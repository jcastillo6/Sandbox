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
import org.hibernate.validator.constraints.NotEmpty;
import com.jcastillo.exchanger.data.ConversionRate;
import com.jcastillo.exchanger.data.Currency;


@Stateless
@LocalBean
public class MConversionRate implements DAO<ConversionRate> {
	private static final Logger log = Logger.getLogger(MConversionRate.class.getName());
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	
	@Override
	public Optional<ConversionRate> get(long id) {
		if(id<0) {
			throw new IllegalArgumentException("Invalid Id, please check. ID must be greater than 0");
		}
		return Optional.ofNullable(em.find(ConversionRate.class, id));
	}

	@Override
	public List<ConversionRate> getAll() {
		List<ConversionRate> cnvs = em.createNamedQuery(ConversionRate.FIND_ALL,ConversionRate.class).getResultList();
		if(cnvs==null || cnvs.size()==0)
			log.log(Level.WARNING,"The table conversion rate is empty ");
		return cnvs;
	}

	@Override
	public void save(ConversionRate t) {
		em.persist(t);
		
	}

	@Override
	public void update(ConversionRate t, String[] params) {
				
	}

	@Override
	public void delete(ConversionRate t) {
		em.remove(t);
		
	}
	
	/**
	 * Look up the conversion rate base in the currency combination
	 * @param isoCodeFrom
	 * @param isoCodeTo
	 * @return Optional<ConversionRate>
	 */
	public Optional<ConversionRate> getConversionRateByIsoCode(@NotEmpty String isoCodeFrom,@NotEmpty String isoCodeTo) {
		ConversionRate convRate = null;
		try {
			convRate = em.createNamedQuery(ConversionRate.FIND_CONV_RATE_BY_CURRENCY,ConversionRate.class).setParameter("isoCodeFrom", isoCodeFrom).setParameter("isoCodeTo", isoCodeTo).getSingleResult();
		}catch(NoResultException e) {
			log.log(Level.WARNING,"ConvertionRate result not found for currency from: {0} to {1} ",new Object[] {isoCodeFrom,isoCodeTo});
			
			
		}
		
	
		return Optional.ofNullable(convRate);
		
		
	}
	
	/**
	 * update the validto date, this invalidate the conversion rate
	 * @return affected records
	 */
	public int invalidateAll() {
		log.log(Level.WARNING,"Calling invalidating");
		return em.createNamedQuery(ConversionRate.INVALIDATE_ALL).executeUpdate();
		
	}
	
	/**
	 * get the currencies that exist in c_currency but not in c_conversionrate 
	 * @return List<Currency> , list of currencies that dont have records in c_conversionrate
	 */
	public List<Currency> getAllMissingRates() {
		List<Currency> currencies=em.createNamedQuery(ConversionRate.FIND_ALL_MISSING_RATES, Currency.class).getResultList();
		log.log(Level.INFO,"Missing rates size {0}",(currencies!=null)?currencies.size():-1);
		return currencies;
		
	}

	/**
	 * this deletes all the conversion rates
	 */
	public void clean() {
		int count=em.createQuery("DELETE FROM ConversionRate e").executeUpdate();
		log.log(Level.WARNING,"ConvertionRate records deleted {0}",count);
		
	}
	

}
