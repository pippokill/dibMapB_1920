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
public class RegExp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String regexp = "[a-zA-Z]+";
        String test = "dfsdfswerwDFSDS";
        //verifica se una stringa corrisponde ad una espressione regolare
        System.out.println(test.matches(regexp));
        System.out.println("=== Es. SPLIT ===");
        //esempio di split con espressione regolare
        String test1 = "sdfkljsd lkjsd fkl    lksdjf   fslkdjf f  fsldkj";
        String[] split = test1.split("\\s+");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println("=== Es. MATCHER ===");
        //esempio Pattern/Matcher
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher("lsdkjfdskl 32lk4j 23kl4 k3l24j 3k2l4 lk32");
        while (matcher.find()) {
            System.out.println(matcher.group() + ": " + matcher.start() + "-" + matcher.end());
        }
    }

}
