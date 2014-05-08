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
public class Station {
    
    private String name;
    private String address;
    private String number;
    private static String contractName = "Toulouse";
    
    
    /*dynamic*/
    private boolean status;
    private int bikeStands;
    private int availableBikeStands;
    private int availableBikes;

    public Station(String name, String address, String status, int bikeStands,
            int availableBikeStands, int availableBikes, String id) {
        this.name = name.replace("\"", "");
        this.number = id.replace("\"", "");
        this.address = address.replace("\"", "");
        String isOpened = status.replace("\"", "");
        this.status = (isOpened.equalsIgnoreCase("OPEN"));
        this.bikeStands = bikeStands;
        this.availableBikeStands = availableBikeStands;
        this.availableBikes = availableBikes;
    }

    @Override
    public String toString() {
        return "Station{" + "name=" + name + ", address=" + address
                + ", status=" + status + ", bikeStands=" + bikeStands
                + ", availableBikeStands=" + availableBikeStands
                + ", availableBikes=" + availableBikes + '}';
    }
    
    public static String getContractName() {
        return contractName;
    }

    public static void setContractName(String aContractName) {
        contractName = aContractName.replaceAll("\"", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("\"", "");
    }

    public String getAddress() {
        return address.replaceAll("\"", "");
    }

    public void setAddress(String address) {
        this.address = address.replaceAll("\"", "");
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(int bikeStands) {
        this.bikeStands = bikeStands;
    }

    public int getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(int availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number.replace("\"", "");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.number);
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
        final Station other = (Station) obj;
        return Objects.equals(this.number, other.number);
    }
    
}
