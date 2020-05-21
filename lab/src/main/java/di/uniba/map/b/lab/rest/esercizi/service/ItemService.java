/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.rest.esercizi.service;

import com.google.gson.Gson;
import di.uniba.map.b.lab.collection.esercizi.Articolo;
import di.uniba.map.b.lab.rest.esercizi.DBManagerSingleton;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pierpaolo
 */
@Path("item")
public class ItemService {

    @GET
    @Path("/get/{itemid}")
    @Produces("application/json")
    public Response item(@PathParam("itemid") String itemid) {
        Articolo a;
        try {
            a = DBManagerSingleton.getInstance().getArticolo(itemid);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        if (a != null) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(a);
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(String json) {
        Gson gson = new Gson();
        Articolo a = gson.fromJson(json, Articolo.class);
        try {
            DBManagerSingleton.getInstance().insertArticolo(a);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{itemid}")
    @Produces("application/json")
    public Response delete(@PathParam("itemid") String itemid) {
        try {
            DBManagerSingleton.getInstance().removeArticolo(itemid);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

}
