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
import org.iaws.beans.NextPassage;
import org.iaws.parser.Parser;
import org.iaws.services.QueryHandler;
import org.iaws.services.ServiceException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class NextController  extends AbstractController{
    QueryHandler handler = new QueryHandler();
    Parser parser = new Parser();
    //liste de toutes les lignes de la zone d'arrêt
    List<NextPassage> nextPassageList = new ArrayList<>();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("next");
        /*
        * Si une zone d'arrêt n'est pas donné en entrée, la liste des lignes
        * de l'univeristé Paul Sabatier sont affichés
        */
        String codeOperator = handler.UPS_STOP_CODE;
        String name = "Université Paul Sabatier";
        if(request.getParameter("code") != null
                && request.getParameter("name") != null){
            codeOperator = request.getParameter("code");
            name = request.getParameter("name");
        }
        nextPassageList = parser.pGetNextPassageList(
                handler.getLineSchedules(codeOperator));
        if(nextPassageList.isEmpty()){
            throw new ServiceException(
                    "Aucun horaire trouve pour cette ligne.");
        }
        
        model.addObject("nextPassageList", nextPassageList);
        model.addObject("codeOperator", codeOperator);
        model.addObject("areaName", name);
        return model;
    }
}