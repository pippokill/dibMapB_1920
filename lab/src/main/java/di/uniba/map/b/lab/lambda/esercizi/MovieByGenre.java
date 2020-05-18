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
package di.uniba.map.b.lab.lambda.esercizi;

import di.uniba.map.b.lab.dataset.movielens.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pierpaolo
 */
public class MovieByGenre {

    private final Map<String, Set<String>> map = new HashMap<>();

    public void accept(Movie movie) {
        Set<String> genres = movie.getGenres();
        for (String genre : genres) {
            Set<String> s = map.get(genre);
            if (s == null) {
                s = new HashSet<>();
                map.put(genre, s);
            }
            s.add(movie.getMovieID());
        }
    }

    public void combine(MovieByGenre o) {
        for (String genre : o.map.keySet()) {
            Set<String> s = map.get(genre);
            if (s == null) {
                s = new HashSet<>();
                map.put(genre, s);
            }
            s.addAll(o.map.get(genre));
        }
    }

    public Map<String, Set<String>> getMap() {
        return map;
    }

}
