package br.com.lolfood.api.resources;

import br.com.lolfood.service.impl.Database;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/sanitize")
public class UtilApi {

    @POST
    public Response sanitize() {
        Database.clear();
        return Response.ok().build();
    }

}
