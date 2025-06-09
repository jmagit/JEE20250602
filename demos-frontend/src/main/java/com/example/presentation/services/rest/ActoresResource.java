package com.example.presentation.services.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.contracts.domain.services.ActoresService;
import com.example.core.contracts.domain.repositories.Page;
import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.DuplicateKeyException;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;
import com.example.presentation.models.ActorEdit;
import com.example.presentation.models.ErrorMessage;

@Path("/actores")
public class ActoresResource {
	private ActoresService srv;

	@Inject
	public ActoresResource(ActoresService srv) {
		this.srv = srv;
	}

//	@GET
//	@Produces({MediaType.APPLICATION_JSON})
//	public List<ActorEdit> getAll() {
//		return srv.getAll().stream().map(item -> ActorEdit.from(item)).collect(Collectors.toList());
//	}
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAll(@DefaultValue("-1") @QueryParam("page") int page, @DefaultValue("20") @QueryParam("size") int size) {
		if(page < 0)
			return Response.ok(srv.getAll().stream().map(item -> ActorEdit.from(item)).collect(Collectors.toList())).build();
		PageModel<Actor> result = srv.getAll(Page.of(page, size));
		return Response.ok(new PageModel<ActorEdit>(result.getNumber(), result.getSize(), 
				result.getContent().stream().map(item -> ActorEdit.from(item)).collect(Collectors.toList()))).build();
	}
	@GET
	@Path("{id:\\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ActorEdit get(@PathParam("id") final int id) {
		return ActorEdit.from(srv.getOne(id)
	            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)));
	}
	@GET
	@Path("{id:\\d+}/peliculas")
	@Produces({MediaType.APPLICATION_JSON})
	public List<String>  getPelis(@PathParam("id") final int id) {
		Optional<Actor> item = srv.getOne(id);
		if(item.isPresent())
			return item.get().getFilmActors().stream().map(p -> p.getFilm().getTitle()).collect(Collectors.toList());
		throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ActorEdit item, @Context UriInfo uriInfo) {//
		try {
			Actor newItem = srv.add(ActorEdit.from(item));
			return Response.created(URI.create(uriInfo.getAbsolutePath().toString() + "/" + newItem.getActorId())).build();
		} catch (DuplicateKeyException e) {
			throw new WebApplicationException(Response.ok(e.getMessage()).status(Response.Status.CONFLICT).build());
		} catch (InvalidDataException e) {
			throw new WebApplicationException(Response.ok(e.getErrors()).status(Response.Status.BAD_REQUEST).build());
		}
	}
	@PUT
	@Path("{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modify(@PathParam("id") int id, ActorEdit item) {
		if(id != item.getActorId())
			throw new WebApplicationException(Response.ok(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(), "No coinciden los identificadores")).status(Response.Status.BAD_REQUEST).build());
		try {
			srv.modify(ActorEdit.from(item));
		} catch (NotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (InvalidDataException e) {
			throw new WebApplicationException(Response.ok(e.getErrors()).status(Response.Status.BAD_REQUEST).build());
		}
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modify2(ActorEdit item) {
		try {
			srv.modify(ActorEdit.from(item));
		} catch (NotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (InvalidDataException e) {
			throw new WebApplicationException(Response.ok(e.getErrors()).status(Response.Status.BAD_REQUEST).build());
		}
	}
	
	@DELETE
	@Path("{id:\\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") int id) {
		try {
			srv.deleteById(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
