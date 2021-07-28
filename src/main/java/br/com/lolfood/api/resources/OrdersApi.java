package br.com.lolfood.api.resources;

import br.com.lolfood.model.Order;
import br.com.lolfood.service.OrderService;
import br.com.lolfood.util.ApiResponseErrorUtil;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/order")
public class OrdersApi {

    private static final Logger LOG = Logger.getLogger(OrdersApi.class);

    @Inject
    private OrderService service;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(@Valid Order order) {

        LOG.info("create order: " + order);
        try {
            service.createOrder(order);
            return Response.status(201).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@Valid String orderId) {

        LOG.info("Update Order ID: " + orderId);
        try {
            service.updateOrder(orderId);
            return Response.status(OK).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getByRestaurant(@Range(min = 1) @QueryParam("restaurantId") Long id) {

        LOG.info("Find Orders by restaurante ID: " + id);
        try {
            List<Order> orders = service.getOrdersByRestaurant(id);
            return Response.ok(orders).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getById(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("Get Order by ID: " + id);
        try {
            Order order = service.getOrder(id);
            return Response.ok(order).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }
}
