package br.com.lolfood.api.resources;

import br.com.lolfood.model.Restaurant;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/restaurant")
public class RestaurantsApi {

    private static final Logger LOG = Logger.getLogger(RestaurantsApi.class);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createRestaurant(@Valid Restaurant restaurant) {

        LOG.info("create restaurant: " + restaurant);
        return Response.status(CREATED).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateRestaurant(@Valid Restaurant restaurant) {

        LOG.info("Update Restaurant: " + restaurant);
        return Response.status(OK).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getRestaurant(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("get Restaurant: " + id);
        Restaurant restaurant = Restaurant.builder().id(1L).lon(-96.25).lat(59.02).build();
        return Response.ok(restaurant).build();
    }
}
