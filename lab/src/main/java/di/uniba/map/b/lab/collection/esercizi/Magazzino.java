/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.collection.esercizi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pierpaolo
 */
public class Magazzino {

    private final Map<Articolo, Integer> magazzino;

    public Magazzino(Map<Articolo, Integer> magazzino) {
        this.magazzino = magazzino;
    }

    public Magazzino() {
        this.magazzino = new HashMap<>();
    }

    public Map<Articolo, Integer> getMagazzino() {
        return magazzino;
    }

    public void aggiungiArticolo(Articolo articolo) {
        aggiungiArticolo(articolo, 1);
    }

    public void aggiungiArticolo(Articolo articolo, int quantita) {
        Integer q = magazzino.get(articolo);
        if (q == null) {
            magazzino.put(articolo, quantita);
        } else {
            magazzino.put(articolo, q + quantita);
        }
    }

    public void rimuoviArticolo(Articolo articolo) throws QuantitaNonDisponibileException {
        rimuoviArticolo(articolo, 1);
    }

    public void rimuoviArticolo(Articolo articolo, int quantita) throws QuantitaNonDisponibileException {
        if (disopnibile(articolo, quantita)) {
            Integer q = magazzino.get(articolo);
            magazzino.put(articolo, q - quantita);
        } else {
            throw new QuantitaNonDisponibileException();
        }
    }

    public boolean disopnibile(Articolo articolo, int quantita) {
        Integer q = magazzino.get(articolo);
        return (q != null && q >= quantita);
    }

    public boolean disopnibile(Articolo articolo) {
        return disopnibile(articolo, 1);
    }

    public boolean calcolaCarrello(Carrello carrello, Utente utente) {
        boolean valido = true;
        double costo = 0;
        for (Map.Entry<Articolo, Integer> articolo : carrello.getArticoli().entrySet()) {
            valido = disopnibile(articolo.getKey(), articolo.getValue());
            if (!valido) {
                break;
            }
            costo += articolo.getKey().getCostoUnitario() * articolo.getValue();
        }
        if (valido) {
            carrello.setCostoMerce(costo);
            utente.calcolaSpeseSpedizione(carrello);
        }
        return valido;
    }

    public Ordine effettuaOrdine(String idOrdine, Utente utente, Carrello carrello) throws QuantitaNonDisponibileException {
        Ordine ordine = new Ordine(idOrdine, utente.getId(), carrello);
        // gli articoli acquistati devono essere rimossi dal magazzino
        for (Map.Entry<Articolo, Integer> articolo : ordine.getArticoli().entrySet()) {
            rimuoviArticolo(articolo.getKey(), articolo.getValue());
        }
        utente.getStoricoOrdini().add(ordine);
        return ordine;
    }

    public Articolo cercaArticolo(String id) throws ArticoloNonTrovatoException {
        for (Articolo a : magazzino.keySet()) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        throw new ArticoloNonTrovatoException();
    }

    public List<Articolo> ricercaDisponibilita(int quantita) {
        List<Articolo> articoli = new ArrayList<>();
        for (Map.Entry<Articolo, Integer> articolo : magazzino.entrySet()) {
            if (articolo.getValue() < quantita) {
                articoli.add(articolo.getKey());
            }
        }
        return articoli;
    }

}
