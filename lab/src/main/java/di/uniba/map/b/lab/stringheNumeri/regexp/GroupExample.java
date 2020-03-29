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
package di.uniba.map.b.lab.stringheNumeri.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pierpaolo
 */
public class GroupExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //una sequenza di numeri seguita da 1 o massimo 3 lettere minuscole
        String regexp = "([0-9]+)([a-z]{1,3})";
        Pattern pattern = Pattern.compile(regexp);
        String str = "9843989jf 39203920jie 32122i";
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) { //cicla sui match
            int gc = matcher.groupCount(); //restituisce il numero di gruppi
            //il gruppo 0 corrisponde all'intero matching
            for (int i = 0; i <= gc; i++) {
                System.out.println(matcher.group(i));
            }
            System.out.println();
        }
    }

}
