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
public class Destination {
    /**
     * Nom de l'arrêt.
    */
    private String name;
    /**
     * Nom de la ville.
     */
    private String cityName;
    /**
     * Id de la destination.
     */
    private String id;
    /**
     * Les lignes de la destination
     */
    private List<Line> lines;
    /**
     * 
     * @param name
     * @param id 
     */
    public Destination(String name, String id) {
        lines = new ArrayList<>();
        this.name = name.replaceAll("\"", "");
        this.id = id.replaceAll("\"", "");
    }
    /**
     * 
     * @return 
     */
    public List<Line> getLines() {
        return lines;
    }
    /**
     * 
     * @param lines 
     */
    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name.replaceAll("\"", "");
    }
    /**
     * 
     * @return 
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * 
     * @param cityName 
     */
    public void setCityName(String cityName) {
        this.cityName = cityName.replaceAll("\"", "");
    }
    /**
     * 
     * @return 
     */
    public String getId() {
        return id;
    }
    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id.replaceAll("\"", "");
    }
    /**
     * 
     * @param line 
     */
    public void addLine(Line line){
        lines.add(line);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Destination other = (Destination) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Destination{" + "name=" + name + ", cityName="
                + cityName + ", id=" + id + ", lines=" + lines + '}';
    }
    
    
}
