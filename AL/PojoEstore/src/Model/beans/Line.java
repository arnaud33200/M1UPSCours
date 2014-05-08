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

import java.util.Objects;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class Line {
    /**
     * Nom de la ligne.
     */
    private String name;
    /**
     * Numero de la ligne (88, 2s, B).
     */
    private String shortName;
    /**
     * identifiant de la ligne.
     */
    private String id;
    private String bgXmlColor;
    private String fgXmlColor;
    private String color;
    private Social social;
    /**
     * Default constructor
     * @param name
     * @param shortName
     * @param id 
     */
    public Line(String name, String shortName, String id) {
        this.name = name.replaceAll("\"", "");
        this.shortName = shortName.replaceAll("\"", "");
        this.id = id.replaceAll("\"", "");
    }

    @Override
    public String toString() {
        return "Line{" + "name=" + name + ", shortName="
                + shortName + ", id=" + id + '}';
    }
    /**
     * 
     * @return name le nom de la  ligne.
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
     * @return bgXmlColor
     */
    public String getBgXmlColor() {
        return bgXmlColor;
    }
    /**
     * 
     * @param bgXmlColor 
     */
    public void setBgXmlColor(String bgXmlColor) {
        this.bgXmlColor = bgXmlColor.replaceAll("\"", "");
    }
    /**
     * 
     * @return 
     */
    public String getFgXmlColor() {
        return fgXmlColor;
    }
    /**
     * 
     * @param fgXmlColor 
     */
    public void setFgXmlColor(String fgXmlColor) {
        this.fgXmlColor = fgXmlColor.replaceAll("\"", "");
    }
    /**
     * 
     * @return shortName le numero de la ligne.
     */
    public String getShortName() {
        return shortName;
    }
    /**
     * 
     * @param line 
     */
    public void setShortName(String line) {
        this.shortName = line.replaceAll("\"", "");
    }
    /**
     * 
     * @return color
     */
    public String getColor() {
        return color;
    }
    /**
     * 
     * @param color 
     */
    public void setColor(String color) {
        this.color = color.replaceAll("\"", "");
    }
    /**
     * 
     * @return id l'identifiant de la ligne.
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Line other = (Line) obj;
        return Objects.equals(this.id, other.id);
    }

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }
}
