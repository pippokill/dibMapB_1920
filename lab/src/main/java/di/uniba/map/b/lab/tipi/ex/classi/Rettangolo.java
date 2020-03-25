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
public class Rettangolo extends FiguraComp {

    public Rettangolo(double lato1, double lato2) {
        super(lato1, lato2);
    }

    @Override
    public double area() {
        return dim1 * dim2;
    }

    @Override
    public String toString() {
        return "Sono un rettangolo con lati: " + dim1 + " e " + dim2;
    }

}
