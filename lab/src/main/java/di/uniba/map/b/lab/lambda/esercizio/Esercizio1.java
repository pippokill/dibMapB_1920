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
package di.uniba.map.b.lab.lambda.esercizio;

import di.uniba.map.b.lab.dataset.movielens.Movielens;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author pierpaolo
 */
public class Esercizio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Movielens movielens = new Movielens();
            movielens.load(new File("resources/movielens1M"));
            //Effettuare le seguenti operazioni utilizzando il più possibile gli stream e le espressioni lambda
            //1. raggruppare tutti gli utenti di genere femminile con età maggiore o uguale a 25 anni in base all’occupazione
            //INSERT CODE HERE
            //2. raggruppare tutti gli utenti di genere maschile che hanno come occupazione "unemployed" e raggrupparli in base allo zipcode
            //INSERT CODE HERE
            //3. raggruppare tutti gli utenti che hanno come occupazione "unemployed" e contare quanti utenti ci sono per ogni classe di età
            //INSERT CODE HERE
            //4. contare quanti movie hanno un rating >3
            //INSERT CODE HERE
            //5. calcolare per ogni movie il rating medio dato dagli utenti
            //INSERT CODE HERE
            //6. calcolare il rating medio dei movie di genere “Comedy”
            //INSERT CODE HERE
            //7. calcolare il rating medio raggruppando per genere del movie
            //INSERT CODE HERE
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
