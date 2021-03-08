package com.jcastillo.exchanger.model;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.jcastillo.exchanger.data.Currency;



@Stateless
@LocalBean
public class MCurrency implements DAO<Currency> {
	private static final  Logger log = Logger.getLogger(MCurrency.class.getName());
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	

	

	@Override
	public Optional<Currency> get(long id) {
		if(id<0) {
			throw new IllegalArgumentException("Invalid Id, please check. ID must be greater than 0");
		}
		return Optional.ofNullable(em.find(Currency.class, id));
	}

	@Override
	public List<Currency> getAll() {
		List<Currency> cnvs = em.createNamedQuery(Currency.FIND_ALL,Currency.class).getResultList();
		return cnvs;
	}

	@Override
	public void save(Currency t) {
		em.persist(t);
		
	}

	@Override
	public void update(Currency t, String[] params) {
				
	}

	@Override
	public void delete(Currency t) {
		em.remove(t);
		
	}
	
	/**
	 * find currency by isocode
	 * @param isoCode 
	 * @return Optional<Currency> 
	 */
	public Optional<Currency> getCurrencyByISOCode(@NotNull @NotEmpty String isoCode) {
		log.log(Level.INFO,"looking for currency {0}",isoCode);
		Currency currency=null;
		try {
			currency= em.createNamedQuery(Currency.FIND_BY_ISO_CODE, Currency.class).setParameter("isoCode", isoCode).getSingleResult();
			log.log(Level.INFO,"Currency found {0}",isoCode);
		}catch(NoResultException e) {
			log.log(Level.WARNING,"IsoCode Not found: {0}",isoCode);
			
		}
		if(currency==null) {
			log.log(Level.WARNING,"Currency not found {0}",isoCode);
		}
					
		return Optional.ofNullable(currency);
	}

}
