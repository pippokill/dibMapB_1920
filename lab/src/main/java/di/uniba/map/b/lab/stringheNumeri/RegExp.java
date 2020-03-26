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
package di.uniba.map.b.lab.stringheNumeri;

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
        String test = "pippo..pi@topolino.it";
        //System.out.println(test.matches("[A-Za-z][\\w]+\\.[A-Za-z][\\w]+@[\\w]+\\.[A-Za-z]{2,3}"));
        System.out.println(test.matches("[a-zA-Z][a-z0-9]+(\\.)?[a-z0-9]+[@][a-z0-9]+\\.(com|it|org)"));
        String test1="sdfkljsd lkjsd fkl    lksdjf   fslkdjf f  fsldkj";
        String[] split = test1.split("\\s+");
        for (String s:split) {
            System.out.println(s);
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher("lsdkjfdskl 32lk4j 23kl4 k3l24j 3k2l4 lk32");
        while (matcher.find()) {
            System.out.println(matcher.start()+"-"+matcher.end());
        }
    }

}
