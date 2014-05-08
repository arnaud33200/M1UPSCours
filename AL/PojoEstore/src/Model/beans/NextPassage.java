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

import java.util.Date;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class NextPassage {
    /**
     * numero de la ligne
     */
    private String lineNumber;
    /**
     * id de l'arrêt.
     */
    private String stopId;
    /**
     * destination.
     */
    private String destinationName;
    /**
     * Date de départ.
     */
    private Date dateTime;
    /**
     * heure et minute.
     */
    private String hoursMins;
    /**
     * 
     * @param lineId
     * @param stopId
     * @param destination
     * @param dateTime 
     */
    public NextPassage(String lineId, String stopId,
            String destination, Date dateTime) {
        this.lineNumber = lineId.replaceAll("\"", "");
        this.stopId = stopId.replaceAll("\"", "");
        this.destinationName = destination.replaceAll("\"", "");
        this.dateTime = dateTime;
        hoursMins = dateTime.getHours()
                + ":"
                + dateTime.getMinutes();
    }

    public NextPassage() {
        
    }

    @Override
    public String toString() {
        return "Next{" + "lineId=" + lineNumber +
                ", stopId=" + stopId + ", destination="
                + destinationName + ", dateTime=" + dateTime + '}';
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber.replaceAll("\"", "");
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId.replaceAll("\"", "");
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName.replaceAll("\"", "");
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        hoursMins = dateTime.getHours()
                + ":"
                + dateTime.getMinutes();
    }

    public String getHoursMins() {
        return hoursMins;
    }

    public void setHoursMins(String hoursMins) {
        this.hoursMins = hoursMins;
    }
    
    
}
