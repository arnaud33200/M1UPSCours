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
package org.iaws.controller;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iaws.beans.Pair;
import org.iaws.beans.Station;
import org.iaws.parser.Parser;
import org.iaws.services.QueryHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class VelibController extends AbstractController{
    QueryHandler handler = new QueryHandler();
    Parser parser = new Parser();
    List<Station> stationList = new ArrayList<>();;
    List<Pair<String, String>> nameAddressStation  = new ArrayList<>();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView model = new ModelAndView("velib");
        String veloRes = handler.getStations();
        updateList(veloRes);
	model.addObject("nameAddress", nameAddressStation);
        model.addObject("stations", stationList);
        return model;
    }
    
    private void updateList(String json) {
        stationList = parser.pGetStationList(json);
        for (Station station : stationList) {
            nameAddressStation.add(new Pair(station.getName(),
                    station.getAddress()));
        }
    }

}
