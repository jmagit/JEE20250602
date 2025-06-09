package com.example.infraestructure.facades;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.contracts.remotes.ActoresProxy;
import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.presentation.models.ActorRemote;

@Stateless
public class ActoresProxyImpl implements ActoresProxy {
	private static final String URL_BASE = "http://localhost:8080/Datos/api/actores/";

	protected Client client;
	private static final Logger logger = Logger.getLogger(ActoresProxyImpl.class.getName());

	@PostConstruct
	private void init() {
		client = ClientBuilder.newClient();
	}

	@PreDestroy
	private void clean() {
		client.close();
	}

	@Override
	public List<ActorRemote> getAll() {
		return client.target(URL_BASE).request(MediaType.APPLICATION_JSON).get(new GenericType<List<ActorRemote>>() {
		});
	}

	@Override
	public PageModel<ActorRemote> getPage(int page, int size) {
		return client.target(URL_BASE).queryParam("page", page).queryParam("size", size)
				.request(MediaType.APPLICATION_JSON).get(new GenericType<PageModel<ActorRemote>>() {
				});
	}

	@Override
	public ActorRemote getOne(int id) {
		return client.target(URL_BASE).path("{id}").resolveTemplate("id", id).request(MediaType.APPLICATION_JSON)
				.get(ActorRemote.class);
	}

	@Override
	public ActorRemote add(ActorRemote item) throws InvalidDataException, NotFoundException {
		if(item == null)
			throw new IllegalArgumentException("No puede ser nulo");
		try {
			Response response = client.target(URL_BASE)
					.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(item, MediaType.APPLICATION_JSON), Response.class);
			switch (response.getStatus()) {
			case 201:
				String[] parts = response.getLocation().getPath().split("/");
				return getOne(Integer.parseInt(parts[parts.length - 1]));
			case 400:
				throw new InvalidDataException(
						response.getEntity() == null ? "Sin informacion adicional" : response.readEntity(String.class));
			case 404:
				throw new NotFoundException();
			default:
				throw new RuntimeException("StatusCode: " + response.getStatus());
			}
		} catch (NumberFormatException e) {
			throw new InvalidDataException("Invalid location");
		} catch (InvalidDataException | NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ActorRemote modify(ActorRemote item) throws InvalidDataException, NotFoundException {
		if(item == null)
			throw new IllegalArgumentException("No puede ser nulo");
		try {
			Response response = client.target(URL_BASE)
					.path("{id}").resolveTemplate("id", item.getActorId())
					.request(MediaType.APPLICATION_JSON)
					.put(Entity.entity(item, MediaType.APPLICATION_JSON), Response.class);
			switch (response.getStatus()) {
			case 204:
				return getOne(item.getActorId());
			case 400:
				throw new InvalidDataException(
						response.getEntity() == null ? "Sin informacion adicional" : response.getEntity().toString());
			case 404:
				throw new NotFoundException();
			default:
				throw new RuntimeException("StatusCode" + response.getStatus());
			}
		} catch (InvalidDataException | NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(ActorRemote item) throws InvalidDataException, NotFoundException {
		if(item == null)
			throw new IllegalArgumentException("No puede ser nulo");
		deleteById(item.getActorId());
	}

	@Override
	public void deleteById(Integer id) throws InvalidDataException, NotFoundException {
		try {
			Response response = client.target(URL_BASE)
					.path("{id}").resolveTemplate("id", id)
					.request(MediaType.APPLICATION_JSON)
					.delete(Response.class);
			switch (response.getStatus()) {
			case 204: return;
			case 400:
				throw new InvalidDataException(
						response.getEntity() == null ? "Sin informacion adicional" : response.getEntity().toString());
			case 404:
				throw new NotFoundException();
			default:
				throw new RuntimeException("StatusCode" + response.getStatus());
			}
		} catch (InvalidDataException | NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
