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

import java.util.Map;

/**
 *
 * @author pierpaolo
 */
public class UtenteNormale extends Utente {

    public UtenteNormale() {
    }

    public UtenteNormale(String id, String username) {
        super(id, username);
    }

    @Override
    public void calcolaSpeseSpedizione(Carrello carrello) {
        double pesoGr = 0;
        for (Map.Entry<Articolo, Integer> articolo : carrello.getArticoli().entrySet()) {
            pesoGr += articolo.getKey().getPesoGrammi() * articolo.getValue();
        }
        if (pesoGr < 1000) {
            carrello.setCostoSpedizione(2);
        } else {
            carrello.setCostoSpedizione(Math.round(pesoGr / 1000) * 2);
        }
    }

}
