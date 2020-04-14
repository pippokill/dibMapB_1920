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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pierpaolo
 */
public class Carrello {

    private final Map<Articolo,Integer> articoli;

    private double costoMerce;

    private double costoSpedizione;

    public Carrello() {
        articoli=new HashMap<>();
    }

    public Carrello(Map<Articolo, Integer> articoli) {
        this.articoli = articoli;
    }

    public Map<Articolo, Integer> getArticoli() {
        return articoli;
    }
    
    public void aggiungiArticolo(Articolo articolo) {
        aggiungiArticolo(articolo, 1);
    }

    public void aggiungiArticolo(Articolo articolo, int quantita) {
        Integer q = articoli.get(articolo);
        if (q == null) {
            articoli.put(articolo, quantita);
        } else {
            articoli.put(articolo, q + quantita);
        }
    }
    
    //si potrebbero implemetare anche i metodi per rimuovere articoli dal carrello, ma nell'esercizio non sono utilizzati

    public double getCostoMerce() {
        return costoMerce;
    }

    public void setCostoMerce(double costoMerce) {
        this.costoMerce = costoMerce;
    }

    public double getCostoSpedizione() {
        return costoSpedizione;
    }

    public void setCostoSpedizione(double costoSpedizione) {
        this.costoSpedizione = costoSpedizione;
    }

    public double getCostoTotale() {
        return costoMerce + costoSpedizione;
    }

}
