package br.com.lolfood.api.resources;

import br.com.lolfood.model.Order;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/order")
public class OrdersApi {

    private static final Logger LOG = Logger.getLogger(OrdersApi.class);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(@Valid Order order) {

        LOG.info("create order: " + order);
        return Response.status(CREATED).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@Valid String orderId) {

        LOG.info("Update Order ID: " + orderId);
        return Response.status(OK).build();
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getByRestaurant(@Range(min = 1) @QueryParam("restaurantId") Long id) {

        LOG.info("Find Orders by restaurante ID: " + id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getById(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("Get Order by ID: " + id);
        return Response.ok().build();
    }
}
