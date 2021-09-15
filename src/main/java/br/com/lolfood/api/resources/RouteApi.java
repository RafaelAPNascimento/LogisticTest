package br.com.lolfood.api.resources;

import br.com.lolfood.model.Route;
import br.com.lolfood.service.RouteService;
import br.com.lolfood.util.ApiResponseErrorUtil;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.json.JSONObject;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/routes")
public class RouteApi {

    private static final Logger LOG = Logger.getLogger(RouteApi.class);

    @Inject
    private RouteService routeService;

    @GET
    @Produces(APPLICATION_JSON)
    public Response get() {
        LOG.info("GET Suggested Routes");
        try {
            Route[] routes = routeService.getSuggestedRoutes();
            return Response.ok(routes).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response confirm(@NotNull String orderId) {

        Long id = new JSONObject(orderId).getLong("id");;

        LOG.info(String.format("Route Confirmation: route Id [ %s ]", id));

        try {
            Route route = routeService.confirm(id);
            return Response.ok(route).build();
        }
        catch (Exception e) {
            return ApiResponseErrorUtil.handle(e);
        }
    }
}
