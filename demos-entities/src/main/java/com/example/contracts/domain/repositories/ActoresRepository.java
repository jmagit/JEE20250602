package com.example.contracts.domain.repositories;

import com.example.core.contracts.domain.repositories.PageableRepository;
import com.example.domain.entities.Actor;

public interface ActoresRepository extends PageableRepository<Actor, Integer> {

	// No additional methods are required here as the base Repository interface
	// already provides the necessary CRUD operations for Actor entities.
	// If specific methods for Actor are needed, they can be added here.

}
