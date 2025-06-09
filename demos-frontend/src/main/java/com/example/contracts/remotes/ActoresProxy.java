package com.example.contracts.remotes;

import java.util.List;

import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.presentation.models.ActorRemote;

public interface ActoresProxy {

	List<ActorRemote> getAll();

	PageModel<ActorRemote> getPage(int page, int size);

	ActorRemote getOne(int id);

	ActorRemote add(ActorRemote item) throws InvalidDataException, NotFoundException;

	ActorRemote modify(ActorRemote item) throws InvalidDataException, NotFoundException;

	void delete(ActorRemote item) throws InvalidDataException, NotFoundException;

	void deleteById(Integer id) throws InvalidDataException, NotFoundException;

}