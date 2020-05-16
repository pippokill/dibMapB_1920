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

import java.util.Objects;

/**
 *
 * @author pierpaolo
 */
public class Articolo {

    private String id;

    private String descrizione;

    private double costoUnitario;

    private double pesoGrammi;

    public Articolo() {
    }

    public Articolo(String id) {
        this.id = id;
    }

    public Articolo(String id, String descrizione, double costoUnitario, double pesoGrammi) {
        this.id = id;
        this.descrizione = descrizione;
        this.costoUnitario = costoUnitario;
        this.pesoGrammi = pesoGrammi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getPesoGrammi() {
        return pesoGrammi;
    }

    public void setPesoGrammi(double pesoGrammi) {
        this.pesoGrammi = pesoGrammi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Articolo other = (Articolo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Articolo{" + "id=" + id + ", descrizione=" + descrizione + ", costoUnitario=" + costoUnitario + ", pesoGrammi=" + pesoGrammi + '}';
    }

    public String toPrettyString() {
        return id + ": " + descrizione + ", " + costoUnitario + "€ , " + pesoGrammi + "gr.";
    }

}
