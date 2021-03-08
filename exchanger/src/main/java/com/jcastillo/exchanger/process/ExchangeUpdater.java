package com.jcastillo.exchanger.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import org.hibernate.validator.constraints.NotEmpty;
import com.jcastillo.exchanger.data.ConversionRate;
import com.jcastillo.exchanger.data.Currency;
import com.jcastillo.exchanger.model.MConversionRate;
import com.jcastillo.exchanger.model.MCurrency;
import com.jcastillo.exchanger.spi.ExchangeRate;
import com.jcastillo.exchanger.spi.Exchanger;

/**
 * this class is responsible of getting the latest conversion rates availables
 * @author Jorge Castillo
 *
 */
@Singleton
public class ExchangeUpdater {
	private static final Logger log = Logger.getLogger(ExchangeUpdater.class.getName());
	private static final BigDecimal ONE = new BigDecimal(1);
	private static final int DEFAULT_SCALE = 4;
	@Inject
	private Exchanger exchanger;
	@Inject
	private MCurrency mcurrency;
	@Inject
	private MConversionRate mCnvRate;
	@Inject
	private Client client;

	/**
     * Default constructor. 
     */
    public ExchangeUpdater() {
        // TODO Auto-generated constructor stub
    }
	


    /**
     * This method execute the task of bringing the latest rates available
     */
    @Lock(LockType.WRITE)
	@Schedule(minute ="*/30", hour = "8-20" , dayOfWeek = "Mon-Fri", month="*", year="*", info="MyTimer")
    public void scheduledTimeoutUpdater() {
       log.entering(ExchangeUpdater.class.getName(),"scheduledTimeout");
		
		
		List<ExchangeRate> rates = exchanger.getLatest(client);
		if(rates!=null && rates.size()>0) {
			invalidateAll();
		}
		
		boolean success=updateRates(rates);
		if(success)
			updateCombinations();
			
        
    }
	
	/**
	 * Daily clean
	 */
	@Schedule( hour = "21" , dayOfWeek = "Mon-Fri", month="*", year="*", info="MyTimer")
    public void scheduledTimeoutCleaner() {
		mCnvRate.clean();
	}
	
	
	

	/**
	 * Close all conversion rates
	 */
	private void invalidateAll() {
		int invalidated = mCnvRate.invalidateAll();
		log.log(Level.WARNING,"Convertion rate records invalidated {0} ",invalidated);
		
		
	}

	/**
	 * Look for missing conversion rates and creates all
	 */
	private void updateCombinations() {
		log.log(Level.INFO, "Update combinations");
		List<Currency> currencies = mCnvRate.getAllMissingRates();
		log.log(Level.WARNING,"Missing rates for currencies {0}",currencies.size() );
		for(Currency currency:currencies) {
			List<ExchangeRate> rates =  exchanger.getRatesByBaseCurrency(client, currency.getIsoCode());
			log.log(Level.INFO,"Misssing rates for currency {0}",currency.getIsoCode());
			if(rates!=null&&!rates.isEmpty()) {
				int count =0;
				Currency currencyFrom = mcurrency.getCurrencyByISOCode(rates.get(0).getIsoCodeFrom()).get();
				for(ExchangeRate rate:rates) {
					Currency currencyTo = mcurrency.getCurrencyByISOCode(rate.getIsoCodeTo()).get();
					ConversionRate crate = new ConversionRate();
					crate.setCurrencyFrom(currencyFrom);
					crate.setCurrencyTo(currencyTo);
					crate.setMultiplyRate(rate.getRate());
					crate.setDivideRate(ONE.divide(rate.getRate(), DEFAULT_SCALE, RoundingMode.HALF_UP));
					crate.setValidFrom(new Date());
					mCnvRate.save(crate);	
					count++;
					
				}
				
				log.log(Level.WARNING,"Created {0} conversion rates for {1}",new Object[] {count,currencyFrom.getIsoCode()});
			}else {
				log.log(Level.WARNING,"Not found missing rates for currency {0}",currency.getIsoCode());
			}
		
		}		
		
	}

	/**
	 * Create rates 
	 * @param rates
	 * @return success 
	 */
	private boolean updateRates(@NotEmpty List<ExchangeRate> rates) {
		int count = 0;
		log.log(Level.INFO,"Rates to create {0}",rates.size());
		Currency currencyFrom = mcurrency.getCurrencyByISOCode(rates.get(0).getIsoCodeFrom()).orElse(null);
		if(currencyFrom==null) {
			currencyFrom=createCurrency(rates.get(0).getIsoCodeFrom());
		}
		
		for(ExchangeRate rate:rates) {
			Currency currencyTo = mcurrency.getCurrencyByISOCode(rate.getIsoCodeTo()).orElse(null);
			if(currencyTo==null) {
				currencyTo=createCurrency(rate.getIsoCodeTo());
			}
			ConversionRate crate = new ConversionRate();
			crate.setCurrencyFrom(currencyFrom);
			crate.setCurrencyTo(currencyTo);
			crate.setMultiplyRate(rate.getRate());
			crate.setDivideRate(ONE.divide(rate.getRate(), DEFAULT_SCALE, RoundingMode.HALF_UP));
			crate.setValidFrom(new Date());
			mCnvRate.save(crate);	
			count++;
			
		}
		log.log(Level.WARNING, "Created rates: {0}",count);
		return (count>0)?true:false;
	}

	/**
	 * create currency
	 * @param isoCode
	 * @return created currency
	 */
	private Currency createCurrency(String isoCode) {
		log.log(Level.INFO,"Creating currency {0}",isoCode);
		Currency crrcy = new Currency();
		crrcy.setCurSymbol(isoCode);
		crrcy.setName(isoCode);
		crrcy.setIsoCode(isoCode);
		mcurrency.save(crrcy);
		return crrcy;

	}
	
	
	
	
}