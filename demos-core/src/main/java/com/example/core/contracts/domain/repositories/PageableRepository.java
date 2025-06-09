package com.example.core.contracts.domain.repositories;

public interface PageableRepository<E, K> extends Repository<E, K> {
	PageModel<E> findAll(Page page);
}
