package com.jcastillo.exchanger.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jcastillo.exchanger.data.CurrencyType;

public class MCurrencyType implements DAO<CurrencyType>{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Override
	public Optional<CurrencyType> get(long id) {
		if(id<0) {
			throw new IllegalArgumentException("Invalid Id, please check. ID must be greater than 0");
		}
		return Optional.ofNullable(em.find(CurrencyType.class, id));
	}

	@Override
	public List<CurrencyType> getAll() {
		List<CurrencyType> cnvs = em.createNamedQuery(CurrencyType.FIND_ALL,CurrencyType.class).getResultList();
		return cnvs;
	}

	@Override
	public void save(CurrencyType t) {
		em.persist(t);
		
	}

	@Override
	public void update(CurrencyType t, String[] params) {
				
	}

	@Override
	public void delete(CurrencyType t) {
		em.remove(t);
		
	}

}
