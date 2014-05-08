/**
 * Copyright (C) 2014 Diallo Alpha Oumar Binta && Ladoucette Arnaud
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package Model.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class Stop {
    /**
     * Nom de l'arrêt.
     */
    private String name;
    /**
     * liste des destinations
     */
    private List<Destination> destination;
    /**
     * Identifiant de l'arrêt.
     */
    private String id;
    /**
     * Code opérateur d'un arrêt;
     */
    private String operatorCode;

    public Stop(String name, String id, String operatorCode) {
        this.name = name.replaceAll("\"", "");
        this.id = id.replaceAll("\"", "");
        this.operatorCode = operatorCode.replaceAll("\"", "");
        destination = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("\"", "");
    }

    public List<Destination> getDestination() {
        return destination;
    }

    public void setDestination(List<Destination> aDestination) {
        this.destination = aDestination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.replaceAll("\"", "");
    }
    
    public void addDestination(Destination destination) {
        getDestination().add(destination);
    }
    @Override
    public String toString() {
        return "TisseoStop{" + "name=" + getName()
                + ", codeOp=" + getOperatorCode() + " id=" +id+ " dest=" +""
                + destination.size() + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.operatorCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stop other = (Stop) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.operatorCode, other.operatorCode);
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode.replaceAll("\"", "");
    }
 
}
