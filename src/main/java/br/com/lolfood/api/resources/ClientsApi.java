package br.com.lolfood.api.resources;

import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.Client;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.ClientService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;

@Path("/client")
public class ClientsApi {

    private static final Logger LOG = Logger.getLogger(ClientsApi.class);

    @Inject
    private ClientService service;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(@Valid Client client) {

        LOG.info("Create client: " + client);

        try {
            service.create(client);
            return Response.status(CREATED).build();
        }
        catch (Exception e) {
            return Response.status(500, e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@Valid Client client) {

        LOG.info("Update Client: " + client);
        try {
            service.update(client);
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
    public Response get(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("Get Client: " + id);
        try {
            Client client = service.find(id);
            if (client != null)
                return Response.ok(client).build();
            else
                return Response.status(NO_CONTENT).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(500, e.getMessage()).build();
        }
    }
}
