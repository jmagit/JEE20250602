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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
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

//@Path("/actores")
@Path("/v1/actores")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ActoresResource {
	private ActoresService srv;

	@Inject
	public ActoresResource(ActoresService srv) {
		this.srv = srv;
	}

	@GET
	public Response getAll(@DefaultValue("-1") @QueryParam("page") int page,
			@DefaultValue("20") @QueryParam("size") int size, @HeaderParam("accept-language") String acceptLanguage) {
		if (page < 0)
			return Response.ok(new GenericEntity<List<ActorEdit>>(
					srv.getAll().stream().map(item -> ActorEdit.from(item)).collect(Collectors.toList())) {
			}).build();
		PageModel<Actor> result = srv.getAll(Page.of(page, size));
		return Response
				.ok(new PageModel<ActorEdit>(result.getNumber(), size, result.getTotalElements(),
						result.getContent().stream().map(item -> ActorEdit.from(item)).collect(Collectors.toList())))
				.build();
	}

	@GET
	@Path("{id:\\d+}")
	public ActorEdit get(@PathParam("id") final int id) throws NotFoundException {
		return ActorEdit.from(srv.getOne(id).orElseThrow(() -> new NotFoundException()));
	}

	@GET
	@Path("{id:\\d+}/peliculas")
	public List<String> getPelis(@PathParam("id") final int id) throws NotFoundException {
		Optional<Actor> item = srv.getOne(id);
		if (item.isPresent())
			return item.get().getFilmActors().stream().map(p -> p.getFilm().getTitle()).collect(Collectors.toList());
		throw new NotFoundException();
	}

	@POST
	public Response add(ActorEdit item, @Context UriInfo uriInfo) throws DuplicateKeyException, InvalidDataException {//
			Actor newItem = srv.add(ActorEdit.from(item));
			return Response.created(URI.create(uriInfo.getAbsolutePath().toString() + newItem.getActorId())).build();
	}

	@PUT
	@Path("{id:\\d+}")
	public void modify(@PathParam("id") int id, ActorEdit item) throws NotFoundException, InvalidDataException {
		if (id != item.getActorId())
			throw new InvalidDataException("No coinciden los identificadores");
//			throw new WebApplicationException(Response.ok(
//					new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(), "No coinciden los identificadores"))
//					.status(Response.Status.BAD_REQUEST).build());
		srv.modify(ActorEdit.from(item));
	}

	@PUT
	public void modify2(ActorEdit item) throws NotFoundException, InvalidDataException {
		srv.modify(ActorEdit.from(item));
	}

	@DELETE
	@Path("{id:\\d+}")
	public void remove(@PathParam("id") int id) throws NotFoundException {
		srv.deleteById(id);
	}

}
