package com.example.domain.services;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.contracts.domain.repositories.ActoresRepository;
import com.example.contracts.domain.services.ActoresService;
import com.example.core.annotation.Service;
import com.example.core.contracts.domain.repositories.Page;
import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.DuplicateKeyException;
import com.example.core.domain.exceptions.InvalidDataException;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;

@Stateless
public class ActoresServiceImpl implements ActoresService {
	private ActoresRepository dao;
	
	@Inject
	public ActoresServiceImpl(ActoresRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}

	@Override
	public PageModel<Actor> getAll(Page page) {
		return dao.findAll(page);
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
		if(item.getActorId() != 0 && dao.existsById(item.getActorId()))
			throw new DuplicateKeyException("Ya existe");
		return dao.add(item);
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
		if(!dao.existsById(item.getActorId()))
			throw new NotFoundException();
		return dao.modify(item);
	}

	@Override
	public void delete(Actor item) throws InvalidDataException, NotFoundException {
		if(item == null)
			throw new InvalidDataException("No puede ser nulo");
		dao.delete(item);
	}

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		dao.deleteById(id);
	}

	@Override
	public void repartePremios() {
		// TODO Auto-generated method stub
		
	}

}
