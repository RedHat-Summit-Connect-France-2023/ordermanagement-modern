package io.ordermanagement.order.exception.handler;

import io.ordermanagement.order.exception.ResourceNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<ResourceNotFoundException>{

	public Response toResponse(ResourceNotFoundException ex) {
		
		return Response.status(Status.NOT_FOUND).entity(Status.NOT_FOUND.getStatusCode() + " - " + ex.getMessage()).build();
	}

}
