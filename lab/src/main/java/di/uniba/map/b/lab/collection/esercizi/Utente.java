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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pierpaolo
 */
public abstract class Utente {
    
    private String id;
    
    private String username;
    
    private List<Ordine> storicoOrdini=new ArrayList<>();

    public Utente() {
    }

    public Utente(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ordine> getStoricoOrdini() {
        return storicoOrdini;
    }

    public void setStoricoOrdini(List<Ordine> storicoOrdini) {
        this.storicoOrdini = storicoOrdini;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utente other = (Utente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public abstract void calcolaSpeseSpedizione(Carrello carrello);

    @Override
    public String toString() {
        return "Utente{" + "id=" + id + ", username=" + username + '}';
    }
    
    
    
}
