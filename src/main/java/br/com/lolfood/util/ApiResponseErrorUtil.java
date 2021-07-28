package br.com.lolfood.util;

import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.ResponseError;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class ApiResponseErrorUtil {

    public static Response handle(Exception e) {

        if (e instanceof BusinessException)
            return handleBusinessException((BusinessException) e);
        else
            return handleException(e);
    }

    private static Response handleException(Exception e) {

        ResponseError error = ResponseError.builder().code(INTERNAL_SERVER_ERROR.getStatusCode()).message(e.getMessage()).build();
        return Response.serverError().entity(error).build();
    }

    private static Response handleBusinessException(BusinessException e) {

        if (e.getMessage() == null)
            return Response.status(e.getCode()).build();

        ResponseError error = ResponseError.builder().code(e.getCode()).message(e.getMessage()).build();
        return Response.serverError().entity(error).build();
    }
}
