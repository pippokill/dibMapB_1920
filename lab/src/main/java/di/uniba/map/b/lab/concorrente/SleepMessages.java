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
package di.uniba.map.b.lab.concorrente;

/**
 *
 * @author pierpaolo
 */
public class SleepMessages {

    public static void main(String args[]) throws InterruptedException {
        String importantInfo[] = {
            "Info 1",
            "Info 2",
            "Info 3",
            "Info 4"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            //sospendi per 4 secondi (4000 millisecondi)
            Thread.sleep(4000);
            //Stampa il messaggio
            System.out.println(importantInfo[i]);
        }
    }

}
