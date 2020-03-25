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
public abstract class FiguraComp implements Figura, Comparable {
    
    double dim1;
    
    double dim2;

    public FiguraComp(double dim1, double dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    @Override
    public int compareTo(Object o) {
        // cosa succede se o non è un'istanza di Figura?
        return Double.compare(area(), ((Figura) o).area());
    }

    @Override
    public abstract double area();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Figura) {
            return (area() == ((Figura) obj).area());
        } else {
            return false;
        }
    }

}
