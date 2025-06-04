package com.examples.core.contracts.domain.services;

import java.util.List;
import java.util.Optional;

import com.examples.core.domain.exceptions.DuplicateKeyException;
import com.examples.core.domain.exceptions.InvalidDataException;
import com.examples.core.domain.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();
	
	Optional<E> getOne(K id);
	
	E add(E item) throws DuplicateKeyException, InvalidDataException;
	
	E modify(E item) throws NotFoundException, InvalidDataException;
	
	void delete(E item) throws InvalidDataException;
	void deleteById(K id);
}
