package com.example.infraestructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.contracts.domain.repositories.ActoresRepository;
import com.example.core.annotation.Repository;
import com.example.core.contracts.domain.repositories.Page;
import com.example.core.contracts.domain.repositories.PageModel;
import com.example.core.domain.exceptions.NotFoundException;
import com.example.domain.entities.Actor;

@Repository
@Stateless
public class ActoresRepositoryJPAImpl implements ActoresRepository {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Actor> findAll() {
		return new ArrayList<Actor>(em.createQuery("FROM Actor a", Actor.class).getResultList());
	}

	@Override
	public PageModel<Actor> findAll(Page page) {
		List<Actor> result = em.createQuery("FROM Actor a", Actor.class)
				.setFirstResult(page.getNumber() * page.getSize())
				.setMaxResults(page.getSize())
				.getResultList();
		return new PageModel<Actor>(page.getNumber(), result.size(), new ArrayList<Actor>(result)) ;
	}

	@Override
	public Optional<Actor> findById(Integer id) {
		return Optional.ofNullable(em.find(Actor.class, id));
	}

	@Override
	public Actor add(Actor item) {
        em.persist(item);
        return item;
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException {
		return em.merge(item);
	}

	@Override
	public void delete(Actor item) throws NotFoundException {
		em.remove(item);
	}

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		em.remove(findById(id).orElseThrow(() -> new NotFoundException()));
	}
}
