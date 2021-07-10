package br.com.lolfood.api.resources;

import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.RestaurantService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;

@Path("/restaurant")
public class RestaurantsApi {

    private static final Logger LOG = Logger.getLogger(RestaurantsApi.class);

    @Inject
    private RestaurantService service;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createRestaurant(@Valid Restaurant restaurant) {

        LOG.info("create restaurant: " + restaurant);
        try {
            service.create(restaurant);
            return Response.status(CREATED).build();
        }
        catch (Exception e) {
            return Response.status(500, e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateRestaurant(@Valid Restaurant restaurant) {

        LOG.info("Update Restaurant: " + restaurant);
        try {
            service.update(restaurant);
            return Response.ok().build();
        }
        catch (BusinessException e) {
            return Response.status(e.getCode()).build();
        }
        catch (Exception e) {
            return Response.status(500, e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getRestaurant(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("get Restaurant: " + id);
        try {
            Restaurant restaurant = service.find(id);
            if (restaurant != null)
                return Response.ok(restaurant).build();
            else
                return Response.status(NO_CONTENT).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(500, e.getMessage()).build();
        }
    }
}
