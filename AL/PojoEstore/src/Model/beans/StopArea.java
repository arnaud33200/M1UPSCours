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
public class StopArea {
    /**
     * nom de la ville.
     */
    private String cityName;
    /**
     * identifiant de l'arrêt
     */
    private String id;
    /**
     * nom de l'arrêt.
     */
    private String name;
    /**
     * coordonnées x.
     */
    private String x;
    /**
     * coordonnées y.
     */
    private String y;
    /**
     * ligne de l'arrêt.
     */
    private List<Line> lines;

    public StopArea(String cityName, String id, String name,
            String x, String y) {
        this.cityName = cityName.replaceAll("\"", "");
        this.id = id.replaceAll("\"", "");
        this.name = name.replaceAll("\"", "");
        this.x = x.replaceAll("\"", "");
        this.y = y.replaceAll("\"", "");
        lines = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final StopArea other = (StopArea) obj;
        return Objects.equals(this.id, other.id);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName.replaceAll("\"", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.replaceAll("\"", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("\"", "");
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x.replaceAll("\"", "");
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y.replaceAll("\"", "");
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "StopArea{" + "cityName=" + cityName
                + ", id=" + id + ", name=" + name + '}';
    }
    
    
    
}
