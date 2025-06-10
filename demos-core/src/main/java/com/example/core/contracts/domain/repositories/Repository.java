package com.example.core.contracts.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.example.core.domain.exceptions.NotFoundException;

public interface Repository<E, K> {

	List<E> findAll();

	Optional<E> findById(K id);

	E add(E item);

	E modify(E item) throws NotFoundException;

	void delete(E item) throws NotFoundException;

	void deleteById(K id) throws NotFoundException;
	
	long count();
	
	default boolean existsById(K id) {
		return findById(id).isPresent();
	}

}