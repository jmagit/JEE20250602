package com.example.core.contracts.domain.services;

import com.example.core.contracts.domain.repositories.Page;
import com.example.core.contracts.domain.repositories.PageModel;

public interface PagingAndSortingDomainService<E, K> extends DomainService<E, K> {
//	Iterable<E> getAll(Sort sort);
//	Page<E> getAll(Pageable pageable);
	PageModel<E> getAll(Page page);
}
