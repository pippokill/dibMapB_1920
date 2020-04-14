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

import java.util.List;

/**
 * 
 * @author pierpaolo
 */
public class TestNegozioOnline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        NegozioOnline negozio = new NegozioOnline();

        Articolo art1 = new Articolo("1", "piatto", 3.5, 250);
        Articolo art2 = new Articolo("2", "bicchiere", 2, 200);
        Articolo art3 = new Articolo("3", "forchetta", 1.5, 100);
        Articolo art4 = new Articolo("4", "coltello", 2, 125);
        Articolo art5 = new Articolo("5", "cucchiaio", 1.5, 175);
        Articolo art6 = new Articolo("6", "pentola", 15, 800);
        Articolo art7 = new Articolo("7", "pentola grande", 25, 1500);

        negozio.getMagazzino().aggiungiArticolo(art1, 20);
        negozio.getMagazzino().aggiungiArticolo(art2, 20);
        negozio.getMagazzino().aggiungiArticolo(art3, 40);
        negozio.getMagazzino().aggiungiArticolo(art4, 40);
        negozio.getMagazzino().aggiungiArticolo(art5, 40);
        negozio.getMagazzino().aggiungiArticolo(art6, 10);
        negozio.getMagazzino().aggiungiArticolo(art7, 5);

        Utente ut1 = new UtenteNormale("1", "pippo");
        negozio.getUtenti().add(ut1);
        Utente ut2 = new UtentePrime("2", "pippo PRIME");
        negozio.getUtenti().add(ut2);

        System.out.println();
        Carrello car1 = new Carrello();
        car1.aggiungiArticolo(art1, 2);
        car1.aggiungiArticolo(art7, 3);
        car1.aggiungiArticolo(art2, 5);
        if (negozio.getMagazzino().calcolaCarrello(car1, ut1)) {
            try {
                Ordine ordine = negozio.getMagazzino().effettuaOrdine("1", ut1, car1);
                System.out.println("Costo merce: " + ordine.getCostoMerce());
                System.out.println("Costo spedizione: " + ordine.getCostoSpedizione());
                System.out.println("Costo totale: " + ordine.getCostoTotale());
            } catch (QuantitaNonDisponibileException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Articoli non disponibili");
        }

        System.out.println();
        Carrello car2 = new Carrello();
        car2.aggiungiArticolo(art1, 2);
        car2.aggiungiArticolo(art7, 1);
        car2.aggiungiArticolo(art2, 5);
        if (negozio.getMagazzino().calcolaCarrello(car2, ut2)) {
            try {
                Ordine ordine = negozio.getMagazzino().effettuaOrdine("2", ut2, car2);
                System.out.println("Costo merce: " + ordine.getCostoMerce());
                System.out.println("Costo spedizione: " + ordine.getCostoSpedizione());
                System.out.println("Costo totale: " + ordine.getCostoTotale());
            } catch (QuantitaNonDisponibileException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Articoli non disponibili");
        }

        System.out.println();
        Carrello car3 = new Carrello();
        car3.aggiungiArticolo(art1, 20);
        if (negozio.getMagazzino().calcolaCarrello(car3, ut1)) {
            try {
                Ordine ordine = negozio.getMagazzino().effettuaOrdine("3", ut1, car3);
                System.out.println("Costo merce: " + ordine.getCostoMerce());
                System.out.println("Costo spedizione: " + ordine.getCostoSpedizione());
                System.out.println("Costo totale: " + ordine.getCostoTotale());
            } catch (QuantitaNonDisponibileException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("Articoli non disponibili");
        }

        System.out.println();
        try {
            System.out.println(negozio.getMagazzino().cercaArticolo("2"));
            System.out.println(negozio.getMagazzino().cercaArticolo("11"));
        } catch (ArticoloNonTrovatoException ex) {
            System.err.println(ex.getMessage());
        }
        
        System.out.println();
        try {
            List<Ordine> ordiniUtente = negozio.getOrdiniUtente("2");
            for (Ordine o : ordiniUtente) {
                System.out.println(o);
            }
            negozio.getOrdiniUtente("4");
        } catch (UtenteNonTrovatoException ex) {
            System.err.println(ex.getMessage());
        }
        
        System.out.println();
        List<Articolo> articoli = negozio.getMagazzino().ricercaDisponibilita(4);
        for (Articolo a : articoli) {
            System.out.println(a);
        }
        System.out.println("Articoli più venduti:");
        List<HolderWithScore<Articolo>> piuVenduti = negozio.piuVenduti();
        for (HolderWithScore<Articolo> e : piuVenduti) {
            System.out.println(e);
        }
    }

}
