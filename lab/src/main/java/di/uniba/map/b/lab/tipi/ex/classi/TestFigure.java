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
package di.uniba.map.b.lab.tipi.ex.classi;

/**
 *
 * @author pierpaolo
 */
public class TestFigure {

    public static void main(String[] args) {
        FiguraComp t=new Triangolo(4, 3);
        System.out.println(t);
        FiguraComp r=new Rettangolo(4, 5);
        System.out.println(r);
        System.out.println(r.compareTo(t));
        System.out.println(t.compareTo(r));
        System.out.println(t.equals(r));
        FiguraComp r2=new Rettangolo(2, 3);
        System.out.println(r2);
        System.out.println(r2.compareTo(t));
        System.out.println(t.compareTo(r2));
        System.out.println(t.equals(r2));
    }

}
