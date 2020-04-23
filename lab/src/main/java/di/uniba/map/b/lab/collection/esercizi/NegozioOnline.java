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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  * Progettare la gestione di un negozio online considerando che: – Ogni utente
 * ha un id, username, uno storico degli ordini effettuati – Ci sono due
 * tipologie di utenti: utente normale che paga il costo di spedizione normale e
 * un utente «prime» che paga un costo di spedizione fisso di 1€ ad ordine –
 * Ogni articolo è caratterizzato da un id, una descrizione, un costo unitario,
 * un peso in grammi – Ogni ordine è caratterizzato dall’utente che ha
 * effettuato l’ordine e dalla lista di articoli inseriti nell’ordine e dal
 * costo totale (diviso in costo merce e costo spedizioni) – Il magazzino
 * raccoglie tutti gli articoli disponibili e per ogni articolo la quantità
 * presente nel magazzino – Le spese di spedizione di un ordine dipendono dal
 * peso e sono di 2€ per ogni chilogrammo, se il peso è minore di un chilogrammo
 * il costo è 2€ (gli utenti «prime» pagano 1€ per ogni ordine)
 *
 * Realizzare la seguente funzione: – Simulare il carello di un potenziale
 * acquisto, se il magazzino ha a disposizione gli oggetti presenti nel carrello
 * - Calcolare il costo complessivo del carrello - Calcolare le spese di
 * spedizione - Assegnare all’utente l’ordine inserendolo nello storico degli
 * ordini dell’utente
 *
 * Realizzare le seguente funzioni: – Ricerca di un articolo nel magazzino –
 * Visualizzazione di tutte gli ordini di un utente – Ricerca degli articoli nel
 * magazzino la cui disponibilità è inferiore ad una certa quantità – Lista dei
 * primi 5 articoli più venduti
 *
 * @author pierpaolo
 */
public class NegozioOnline {

    private final Magazzino magazzino;

    private final List<Utente> utenti;

    public NegozioOnline() {
        magazzino = new Magazzino();
        utenti = new ArrayList<>();
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public Utente cercaUtente(String id) throws UtenteNonTrovatoException {
        for (Utente u : utenti) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        throw new UtenteNonTrovatoException();
    }

    public List<Ordine> getOrdiniUtente(String id) throws UtenteNonTrovatoException {
        Utente utente = cercaUtente(id);
        return utente.getStoricoOrdini();
    }

    public List<HolderWithScore<Articolo>> piuVenduti() {
        Map<Articolo, Integer> map = new HashMap<>();
        for (Utente u : utenti) {
            for (Ordine o : u.getStoricoOrdini()) {
                for (Map.Entry<Articolo, Integer> e : o.getArticoli().entrySet()) {
                    Integer v = map.get(e.getKey());
                    if (v == null) {
                        map.put(e.getKey(), e.getValue());
                    } else {
                        map.put(e.getKey(), e.getValue() + v);
                    }
                }
            }
        }
        List<HolderWithScore<Articolo>> list = new ArrayList<>();
        for (Map.Entry<Articolo, Integer> e : map.entrySet()) {
            list.add(new HolderWithScore<>(e.getKey(), e.getValue().doubleValue()));
        }
        Collections.sort(list, Collections.reverseOrder());
        if (list.size() > 5) {
            return list.subList(0, 5);
        } else {
            return list;
        }
    }

}
