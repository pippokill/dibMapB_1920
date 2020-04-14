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

/**
 *
 * @author pierpaolo
 */
public class HolderWithScore<T> implements Comparable<HolderWithScore<T>> {

    private T elem;

    private double score;

    public HolderWithScore(T elem, double score) {
        this.elem = elem;
        this.score = score;
    }
    
    

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(HolderWithScore<T> o) {
        return Double.compare(score, o.score);
    }

    @Override
    public String toString() {
        return "HolderWithScore{" + "elem=" + elem + ", score=" + score + '}';
    }
    
    

}
