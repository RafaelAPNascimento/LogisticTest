package br.com.lolfood.api.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.json.JSONObject;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/routes")
public class RouteApi {

    private static final Logger LOG = Logger.getLogger(RouteApi.class);

    @GET
    @Produces(APPLICATION_JSON)
    public Response get() {
        LOG.info("GET Orders");
        return Response.ok().build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response confirm(@NotNull String orderId) {

        Long id = new JSONObject(orderId).getLong("id");;

        LOG.info(String.format("Confirm Order Id [ %s ]", id));

        return Response.ok().build();
    }
}
