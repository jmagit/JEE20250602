package com.example.presentation.services.rest;

import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.core.domain.exceptions.DuplicateKeyException;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.presentation.models.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class.getName());

	@Override
	public Response toResponse(Throwable ex) {
		// Loguear el error completo en el servidor para depuración
		ErrorMessage error;
		if (ex instanceof NotFoundException || ex instanceof javax.ws.rs.NotFoundException) {
			error = new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "Not found");
		} else if (ex instanceof DuplicateKeyException) {
			error = new ErrorMessage(Response.Status.CONFLICT.getStatusCode(), ex.getMessage());
		} else if (ex instanceof InvalidDataException) {
			error = new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(), 
					((InvalidDataException) ex).getErrors() == null ? ex.getMessage() : "Invalid data",
					((InvalidDataException) ex).getErrors());
		} else if (ex instanceof WebApplicationException) {
			error = new ErrorMessage(((WebApplicationException) ex).getResponse().getStatus(), ex.getMessage());
		} else
			error = new ErrorMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage());
		if (error.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			LOGGER.severe("Unhandled exception occurred: " + ex.getMessage());
			ex.printStackTrace();
		}

		return Response.status(error.getStatus()).entity(error).build();
	}
}