package com.example.infraestructure.facades;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.example.presentation.models.PhotoDTO;

@Stateless
public class PhotosProxy {
//	public List<PhotoDTO> getAll() {
//		Client client = ClientBuilder.newClient();
//		String name = client.target("https://picsum.photos/v2/list?limit=1000")
//		        .request(MediaType.APPLICATION_JSON)
//		        .get(PhotoDTO.class);
//	}
	public PhotoDTO getOne(int id) {
		Client client = ClientBuilder.newClient();
		return client.target("https://picsum.photos/id/{id}/info")
		        .path("{id}").resolveTemplate("id", id)
		        .request(MediaType.APPLICATION_JSON)
		        .get(PhotoDTO.class);
	}
}
