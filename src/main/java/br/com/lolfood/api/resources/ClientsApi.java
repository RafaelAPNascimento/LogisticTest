package br.com.lolfood.api.resources;

import br.com.lolfood.model.Client;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.hibernate.validator.constraints.Range;
import org.jboss.logging.Logger;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/client")
public class ClientsApi {

    private static final Logger LOG = Logger.getLogger(ClientsApi.class);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(@Valid Client client) {

        LOG.info("Create client: " + client);
        return Response.status(CREATED).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response update(@Valid Client client) {

        LOG.info("Update Client: " + client);
        return Response.status(OK).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response get(@Range(min = 1) @PathParam("id") Long id) {

        LOG.info("Get Client: " + id);
        Client client = Client.builder().id(1L).lon(-96.25).lat(59.02).build();
        return Response.ok(client).build();
    }
}
