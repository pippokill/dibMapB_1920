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
package di.uniba.map.b.lab.rest.esercizi;

import di.uniba.map.b.lab.rest.*;
import com.google.gson.Gson;
import di.uniba.map.b.lab.collection.esercizi.Articolo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pierpaolo
 */
public class RESTClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:4321");

        Response resp = target.path("item/get/id1").request(MediaType.APPLICATION_JSON).get();
        System.out.println(resp);
        System.out.println(resp.readEntity(String.class));

        Gson gson = new Gson();
        Articolo a = new Articolo("id200", "Mascherine bucate", 100, 60);
        resp = target.path("item/add").request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(gson.toJson(a), MediaType.APPLICATION_JSON));
        System.out.println(resp);

        resp = target.path("item/delete/id200").request(MediaType.APPLICATION_JSON).delete();
        System.out.println(resp);
    }

}
