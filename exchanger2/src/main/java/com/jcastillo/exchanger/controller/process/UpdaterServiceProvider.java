package com.jcastillo.exchanger.controller.process;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jcastillo.exchanger.model.ConversionRate;
import com.jcastillo.exchanger.model.Currency;
import com.jcastillo.exchanger.repository.ConversionRateRepo;
import com.jcastillo.exchanger.repository.CurrencyRepo;
import com.jcastillo.exchanger.spi.ExchangeProvider;
import com.jcastillo.exchanger.spi.ExchangeRate;





@Component
@Scope(scopeName = "singleton")
public class UpdaterServiceProvider {
	private static final Logger log = Logger.getLogger(UpdaterServiceProvider.class.getName());
	private static final int DEFAULT_SCALE = 4;
	@Autowired
	RestTemplate client;
	@Autowired
	CurrencyRepo currencyRepo;
	@Autowired @Qualifier("Exchangeratesapi")
	ExchangeProvider exchanger;	
	@Autowired
	ConversionRateRepo conversionRateRepo;
	
	
	@Scheduled(cron ="*/30 8-20 * * MON-FRI")
	public void updateRates() {
	       log.entering(UpdaterServiceProvider.class.getName(),"scheduledTimeout");
			
			
			List<ExchangeRate> rates = exchanger.getLatest(client);
			if(rates!=null && rates.size()>0) {
				clearRates();
			}
			
			boolean success=updateRates(rates);
			if(success)
				updateCombinations();
				
		
	}

	/**
	 * Look for missing conversion rates and creates all
	 */
	private void updateCombinations() {
		log.log(Level.INFO, "Update combinations");
		List<Currency> currencies = currencyRepo.findMissingRates();
		log.log(Level.WARNING,"Missing rates for currencies {0}",currencies.size() );
		for(Currency currency:currencies) {
			List<ExchangeRate> rates =  exchanger.getRatesByBaseCurrency(client, currency.getIsoCode());
			log.log(Level.INFO,"Misssing rates for currency {0}",currency.getIsoCode());
			if(rates!=null&&!rates.isEmpty()) {
				int count =0;
				Currency currencyFrom = currencyRepo.findByIsoCode(rates.get(0).getIsoCodeFrom());
				for(ExchangeRate rate:rates) {
					Currency currencyTo = currencyRepo.findByIsoCode(rate.getIsoCodeTo());
					ConversionRate crate = new ConversionRate();
					crate.setCurrencyFrom(currencyFrom);
					crate.setCurrencyTo(currencyTo);
					crate.setMultiplyRate(rate.getRate());
					crate.setDivideRate(BigDecimal.ONE.divide(rate.getRate(), DEFAULT_SCALE, RoundingMode.HALF_UP));
					crate.setValidFrom(new Date());
					conversionRateRepo.save(crate);	
					count++;
					
				}
				
				log.log(Level.WARNING,"Created {0} conversion rates for {1}",new Object[] {count,currencyFrom.getIsoCode()});
			}else {
				log.log(Level.WARNING,"Not found missing rates for currency {0}",currency.getIsoCode());
			}
		
		}		
		
	}

	
	@Scheduled(cron ="*0 21 * * MON-FRI")
	public void clearRates() {
		int invalidated = conversionRateRepo.invalidateAll();
		log.log(Level.WARNING,"Convertion rate records invalidated {0} ",invalidated);
		
		
	}
	
	

	/**
	 * Create rates 
	 * @param rates
	 * @return success 
	 */
	private boolean updateRates(@NotEmpty List<ExchangeRate> rates) {
		int count = 0;
		log.log(Level.INFO,"Rates to create {0}",rates.size());
		if(rates!=null && rates.size()==0) {
			log.log(Level.WARNING,"Rates size == 0, nothing to do");
			return true;
		}
		
		Currency currencyFrom = currencyRepo.findByIsoCode(rates.get(0).getIsoCodeFrom());
		if(currencyFrom==null) {
			currencyFrom=createCurrency(rates.get(0).getIsoCodeFrom());
		}
		
		for(ExchangeRate rate:rates) {
			Currency currencyTo = currencyRepo.findByIsoCode(rate.getIsoCodeTo());
			if(currencyTo==null) {
				currencyTo=createCurrency(rate.getIsoCodeTo());
			}
			ConversionRate crate = new ConversionRate();
			crate.setCurrencyFrom(currencyFrom);
			crate.setCurrencyTo(currencyTo);
			crate.setMultiplyRate(rate.getRate());
			crate.setDivideRate(BigDecimal.ONE.divide(rate.getRate(), DEFAULT_SCALE, RoundingMode.HALF_UP));
			crate.setValidFrom(new Date());
			conversionRateRepo.save(crate);	
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
		currencyRepo.save(crrcy);
		return crrcy;

	}

}
