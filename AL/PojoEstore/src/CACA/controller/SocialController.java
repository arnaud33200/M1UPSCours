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
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iaws.beans.Social;
import org.iaws.parser.Parser;
import org.iaws.services.QueryHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class SocialController extends AbstractController{
    QueryHandler handler = new QueryHandler();
    Parser parser = new Parser();
    Map<String, Social> socialMap = new HashMap<>();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        ModelAndView model = new ModelAndView("social");
        socialMap = parser.pGetReliabilityMap(handler.getReliability()); 
        List<Social> list = new ArrayList<>(socialMap.values());
        /*
        * ajout d'un like ou unlike
        */
        if(request.getParameter("id") != null
                && request.getParameter("type") != null){
            String id = request.getParameter("id");
            Social social = socialMap.get(id);
            if(social == null){
                //new row
                social = new Social(id);
            }
            String type =request.getParameter("type");
            switch (type) {
                case "up":
                    social.addLike();
                    break;
                case "down":
                    social.addUnLike();
                    break;
            }
            //sending to the database
            handler.sendReliability(social.getId(),
                    social.getLike()+"",
                    social.getUnLike()+"", social.getRev());
            //redirection
            response.sendRedirect("social.htm");
        }
        model.addObject("socialList", list);
        return model;
    }
}
