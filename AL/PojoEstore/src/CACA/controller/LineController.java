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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iaws.beans.Destination;
import org.iaws.beans.Line;
import org.iaws.beans.Pair;
import org.iaws.beans.Social;
import org.iaws.beans.Stop;
import org.iaws.parser.Parser;
import org.iaws.services.QueryHandler;
import org.iaws.services.ServiceException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class LineController extends AbstractController{
    QueryHandler handler = new QueryHandler();
    Parser parser = new Parser();
    List<Stop> areaStopList = new ArrayList<>();
    //liste like, unlike
    Map<String, Social> socialMap = new HashMap<>();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("line");
        /*
        * Si une zone d'arrêt n'est pas donné en entrée, la liste des lignes
        * de l'univeristé Paul Sabatier sont affichés
        */
        String areaId = handler.UPS_AREA_CODE;
        if(request.getParameter("areaId") != null){
            areaId = request.getParameter("areaId");
        }
        areaStopList = parser.pGetStopList(handler.getStop(areaId));
        socialMap = parser.pGetReliabilityMap(handler.getReliability());
        if(areaStopList.isEmpty()){
            throw new ServiceException(
                    "Aucune ligne ne correspond à cette zone d'arrêt");
        }
        model.addObject("areaStopList", areaStopList);
        model.addObject("areaStopName", areaStopList.get(0).getName());
        model.addObject("lines", getLineNameAndCode());
        model.addObject("stopNumber", areaStopList.size());

        return model;
    }
    /**
     * Retourne le numero, le code des lignes.
     * @return 
     */
    private List<Pair<String, String>> getLineNameAndCode(){
        List<Pair<String, String>> list = new ArrayList<>();
        ListIterator<Stop> it = areaStopList.listIterator();
        while(it.hasNext()){
            Stop iter= it.next();
            Iterator<Destination> dest = iter.getDestination().iterator();
            while(dest.hasNext()){
                 for(Line line : dest.next().getLines()){
                     Social social = socialMap.get(line.getShortName());
                     if(social != null){
                         line.setSocial(social);
                     }else{
                         line.setSocial(new Social(line.getShortName()));
                     }                                              
                     list.add(
                             new Pair(iter.getOperatorCode(),
                                     line.getShortName()));
                 }
            }
        } 
        return list;
    }
}
