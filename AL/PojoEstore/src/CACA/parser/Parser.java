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

package org.iaws.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iaws.beans.Destination;
import org.iaws.beans.Social;
import org.iaws.beans.Line;
import org.iaws.beans.NextPassage;
import org.iaws.beans.Station;
import org.iaws.beans.Stop;
import org.iaws.beans.StopArea;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class Parser {
    /**
     * Cette fonction récupère les informations relatives à un arrêt.
     * @param elt
     * @return Stop
     */
    private Stop pGetStop(JsonElement elt) {
        Stop stop;
        //on recupère le nom et l'identifiant de l'arrêt
        JsonObject obj = elt.getAsJsonObject();
        String name = obj.get("name").toString();
        String id = obj.get("id").toString();
        //code operateur de l'arrêt
        JsonArray code = obj.getAsJsonArray("operatorCodes");
        JsonElement jsElt = code.get(0);
        JsonObject jsObj = jsElt.getAsJsonObject();
        jsObj = jsObj.getAsJsonObject("operatorCode");
        //creation de l'arrêt.
        stop = new Stop(name, id, jsObj.get("value").toString());
        //on recupère les destinations à partir de cet arrêt
        JsonArray destinations = obj.getAsJsonArray("destinations");
        for (JsonElement destElt : destinations) {
            //la destination
            Destination destination = pGetDestination(destElt);
            stop.addDestination(destination);
        }
        return stop;
    }
    /**
     * Cette fonction recupère une liste de tous les arrêts.
     * @param str
     * @return 
     */
    public List<Stop> pGetStopList(String str) {
        List<Stop> list = new ArrayList<>();
        JsonElement jelement = new JsonParser().parse(str);
        JsonObject jObj = jelement.getAsJsonObject();
        
        //On recupère les arrêts physiques.
        jObj = jObj.getAsJsonObject("physicalStops");
        JsonArray array = jObj.getAsJsonArray("physicalStop");
        for (JsonElement jsonElement : array) {
            //creation de l'arrêt
            Stop stop = pGetStop(jsonElement);
            list.add(stop);
        }
        return list;
    }
    /**
     * Cette fonction récupère les informations relatives à une destination.
     * @param destElt
     * @return 
     */
    private Destination pGetDestination(JsonElement destElt) {
        JsonObject jObj = destElt.getAsJsonObject();
        String name = jObj.get("name").toString();
	String id = jObj.get("id").toString();
        //creation de l'objet
	Destination destination = new Destination(name, id);
	destination.setCityName(jObj.get("cityName").toString());
        //on recupère les lignes
	JsonArray array = jObj.getAsJsonArray("line");
	for (JsonElement current : array) {
            Line line = pGetLine(current);
            destination.addLine(line);
        }
        return destination;
    }
    /**
     * Cette fonction récupère les informations relatives à une ligne.
     * @param eltLine
     * @return 
     */
    private Line pGetLine(JsonElement eltLine) {
        JsonObject objLine = eltLine.getAsJsonObject();
        String name = objLine.get("name").toString();
        String id = objLine.get("id").toString();
        String shortName = objLine.get("shortName").toString();
        //creation de l'objet ligne
        Line line = new Line(name, shortName, id);
        line.setBgXmlColor(objLine.get("bgXmlColor").toString());
        line.setColor(objLine.get("color").toString());
        line.setFgXmlColor(objLine.get("fgXmlColor").toString());
        return line;
    }
    /**
     * Cette fonction récupère les informations relatives à une station.
     * @param elt
     * @return 
     */
    private Station pGetStation(JsonElement elt) {
        JsonObject obj = elt.getAsJsonObject();
        
        String name = obj.get("name").toString();
        String address = obj.get("address").toString();
        int bikeStands = Integer.parseInt(
                obj.get("bike_stands").toString());
        int availableBikes = Integer.parseInt(
                obj.get("available_bikes").toString());
        int availableBikeStands = Integer.parseInt(
                obj.get("available_bike_stands").toString());
        String status = obj.get("status").toString();
        String number = obj.get("number").toString();
        
        return new Station(name, address, status, bikeStands,
                availableBikeStands, availableBikes, number);
    }
        /**
     * Cette fonction retourne la liste des sations.
     * @param json
     * @return 
     */
    public List<Station> pGetStationList(String json) {
        List<Station> list = new ArrayList<>();
        JsonElement elt = new JsonParser().parse(json);
        JsonArray array = elt.getAsJsonArray();
        
        for (JsonElement jsonElement : array) {
            Station station = pGetStation(jsonElement);
            list.add(station);
        }
        return list;
    }
    /**
     * Cette fonction récupère les informations relatives à un prochain passage.
     * @param elt
     * @param nextPassage
     * @return NextPassage le prochain passage de la ligne.
     */
    private NextPassage pGetNextPassage(JsonElement elt,
			NextPassage nextPassage) {
        JsonObject obj = elt.getAsJsonObject();
        //on recupere l'heure de départ
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date nextSchedule = new Date();
        try {
            String time = obj.get("dateTime").toString()
                            .replaceAll("\"", "");
            nextSchedule = dateFormat.parse(time);
        } catch (NullPointerException | ParseException ex) {
            //cette heure est dépassée
            return null;
        }
        //heure de passage
        nextPassage.setDateTime(nextSchedule);
        //objet ligne
        JsonObject objLine = obj.getAsJsonObject("line");
        nextPassage.setLineNumber(objLine.get("shortName").toString());
        //array destination
        JsonArray arrayDest = obj.getAsJsonArray("destination");
        nextPassage.setDestinationName(
                arrayDest.get(0).getAsJsonObject()
                        .get("name").toString());
        return nextPassage;
    }
    /**
     * Cette fonction retourne la liste des prochains passage.
     * @param json
     * @return 
     */
    public List<NextPassage> pGetNextPassageList(String json) {
        List<NextPassage> list = new ArrayList<>();
        //parser sur la chaine
        JsonElement elt = new JsonParser().parse(json);
        //on recupere l'objet departures
        JsonObject obj = elt.getAsJsonObject();
        obj = obj.getAsJsonObject("departures");
        //stop object
        JsonObject objStop = obj.getAsJsonObject("stop");
        String stopId = objStop.get("id").toString();
        //ligne au départ
        JsonArray departureArray = obj.getAsJsonArray("departure");
        for(JsonElement current : departureArray){
            NextPassage next= new NextPassage();
            next.setStopId(stopId);
            pGetNextPassage(current, next);
            if(next.getDateTime() != null){
                list.add(next);
            }
        }
        return list;
    }
    /**
     * Cette fonction retourne la liste des zones d'arrêts.
     * @param json
     * @return List
     */
    public List<StopArea> pGetAreaStopList(String json){
        List<StopArea> list = new ArrayList<>();
        //parser sur la chaine
        JsonElement elt = new JsonParser().parse(json);
        //objets stopAreas
        JsonObject obj = elt.getAsJsonObject().getAsJsonObject("stopAreas");
        //objet StopArea
        JsonArray array = obj.getAsJsonArray("stopArea");
        for(JsonElement current : array){
            String name = current.getAsJsonObject().get("name").toString();
            String cityName = current.getAsJsonObject().get("cityName").toString();
            String id = current.getAsJsonObject().get("id").toString();
            String x = current.getAsJsonObject().get("x").toString();
            String y = current.getAsJsonObject().get("y").toString();
            StopArea stopA = new StopArea(cityName, id, name, x, y);
            JsonArray lines = current.getAsJsonObject().getAsJsonArray("line");
            for(JsonElement line : lines){
                stopA.getLines().add(this.pGetLine(line));
            }
            list.add(stopA);
        }
        return list;
    }
    /**
     *  Cette fonction récupère les informations relatives à une fiabilité.
     * @param elem
     * @return 
     */
    private Social pGetSocial(JsonElement elem) {
        JsonObject obj = elem.getAsJsonObject();
	JsonObject doc = obj.getAsJsonObject("doc");
	int nbLike = Integer.parseInt(doc.get("like").toString()
				.replaceAll("\"", ""));
	int nbUnlike = Integer.parseInt(doc.get("unlike").toString()
				.replaceAll("\"", ""));
	String rev = doc.get("_rev").toString().replaceAll("\"", "");
	String id = doc.get("_id").toString().replaceAll("\"", "");
	return new Social(id, nbLike, nbUnlike, rev);
    }
    /**
     * Cette fonction retourne les fiabilités des lignes.
     * @param json
     * @return Map
     */
    public Map<String, Social> pGetReliabilityMap(String json) {
        Map<String, Social> reliability = new HashMap<>();
	JsonElement elt = new JsonParser().parse(json);
	JsonObject jObj = elt.getAsJsonObject();
	JsonArray array = jObj.getAsJsonArray("rows");
	for (JsonElement jsonElement : array) {
        	Social likeUnlike = pGetSocial(jsonElement);
		JsonObject obj = jsonElement.getAsJsonObject();
		String id = obj.get("id").toString().replaceAll("\"", "");
		reliability.put(id, likeUnlike);
	}
	return reliability;
    }

}
