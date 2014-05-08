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

package org.iaws.services;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.iaws.beans.Station;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class QueryHandler {
    private final String KEY_JCDECAUX = "3a343b19af126f71ce6e4ace6d425ed2e59a704f";
    private final String URL_JCDECAUX = "https://api.jcdecaux.com/vls/v1/";
    private final String KEY_TISSEO = "a03561f2fd10641d96fb8188d209414d8";
    private final String URL_TISSEO = "http://pt.data.tisseo.fr/";
    private final String B_BOX = "1.4685963%2C43.5734438%2C1.4572666%2C43.5573361";
    private final String DATABASE_NAME = "paul_sabatier";
    private final String URL_DATABASE = "http://127.0.0.1:5984/";
    public final String UPS_AREA_CODE = "1970324837185012";
    public final String UPS_STOP_CODE = "5935";
    /**
     * Requete http pour récuperer les données d'une url.
     * @param url
     * @return
     * @throws IOException 
     */
    private InputStream makeCallService(URL url) throws IOException {
        //open the connection
	HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
        //connect to the url
        urlConnect.connect();
        if (urlConnect.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return urlConnect.getInputStream();
        }
        return null;
    }
    /**
     * Cette fonction renvoie les stations de velos.
     * @return 
     */
    public String getStations() {
        String stationUrl = URL_JCDECAUX + "stations?apiKey=" + KEY_JCDECAUX
				+ "&contract=" + Station.getContractName();
        InputStream inputStream = null;
        try {
            //send the request
            inputStream = makeCallService(new URL(stationUrl));
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
    /**
     * Cette fonction renvoie les points d'arrêts d'une zone.
     * @param areaId
     * @return 
     */
    public String getStop(String areaId) {
        InputStream inputStream = null;
        String subwayStop = URL_TISSEO 
                + "stopPointsList?"
                + "bbox=" + B_BOX
                + "&stopAreaId=" + areaId
                + "&format=json&displayLines=1&key="
                + KEY_TISSEO;
        try {
            //send the request
            inputStream = makeCallService(new URL(subwayStop));
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
    /**
     * Cette fonction renvoie les zones d'arrêts près de la zone du GPS.
     * @return 
     */
    public String getStopArea() {
        InputStream inputStream = null;
        String subwayStop = URL_TISSEO 
                + "stopAreasList?"
                + "bbox=" + B_BOX
                + "&format=json&displayLines=1&displayCoordXY=1&key="
                + KEY_TISSEO;
        try {
            //send the request
            inputStream = makeCallService(new URL(subwayStop));
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
    /**
     * Cette fonction renvoie les prochains passages des lignes à un arrêt.
     * @param codeOperator
     * @return String
     */
    public String getLineSchedules(String codeOperator) {
        InputStream inputStream = null;
        
        String subwayStop = URL_TISSEO + "departureBoard?operatorCode="
                + codeOperator + "&format=json&displayRealTime=1"
                +"&number=6&key=" + KEY_TISSEO;
        try {
            //send the request
            inputStream = makeCallService(new URL(subwayStop));
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
    /**
     * Cette fonction renvoie les fiabilités sur les lignes.
     * @return String
     */
    public String getReliability() {
        InputStream inputStream = null;
        
        String url = URL_DATABASE 
                + DATABASE_NAME 
                + "/_all_docs?include_docs=true";
        try {
            //send the request
            inputStream = makeCallService(new URL(url));
        } catch (MalformedURLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
    /**
     * Cette fonction insère les fiabilités d'une ligne dans la base de données.
     * @param id
     * @param nbLike
     * @param nbUnlike
     * @param rev
     * @return String
     */
    public String sendReliability(String id, String nbLike, String nbUnlike,
			String rev) {
        try {
            String url_like = URL_DATABASE
                    + DATABASE_NAME + "/" + id;
            String json = parseReliability(id, nbLike, nbUnlike, rev);
            HttpClient httpCli = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut(url_like);
            httpPut.setEntity(new StringEntity(json));
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            HttpResponse response = httpCli.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == 201) {
                InputStream inputStream = response.getEntity().getContent();
                // check
                if (inputStream != null) {
                    Scanner s = new Scanner(
                            inputStream, "UTF-8").useDelimiter("\\A");
                    return s.hasNext() ? s.next() : "";
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
                Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * cette fonction parse la fiabilité d'une ligne sous format Json.
     * @param id
     * @param nbLike
     * @param nbUnlike
     * @param rev
     * @return String
     */
    private String parseReliability(String id, String nbLike,
			String nbUnlike, String rev) {
        Map<String, String> reliability = new HashMap<>();
	reliability.put("_id", id);
	reliability.put("like", nbLike);
	reliability.put("unlike", nbUnlike);
	if (!rev.equals("null")) {
            reliability.put("_rev", rev);
	}
	String json = new GsonBuilder().create().toJson(reliability, Map.class);
	return json;
    }

}
