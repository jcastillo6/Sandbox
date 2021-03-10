package com.jcastillo.exchanger.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jcastillo.exchanger.data.ConversionRate;
import com.jcastillo.exchanger.data.Currency;
import com.jcastillo.exchanger.model.MConversionRate;
import com.jcastillo.exchanger.model.MCurrency;
import com.jcastillo.exchanger.process.ExchangeUpdater;
/**
 * Session Bean implementation class Convert
 */
@Stateless
@LocalBean
public class CurrencyManagement implements CurrencyManagementLocal {
	private static final int DEFAULT_SCALE=4;
	private static final Logger log = Logger.getLogger(CurrencyManagement.class.getName());
	
	@EJB
	MCurrency mcurrency;
	@EJB
	MConversionRate convRate;
	@Inject
	ExchangeUpdater exUpdater;
	
	/**
     * Default constructor. 
     */
    public CurrencyManagement() {
    }

	@Override
	public List<Currency> findCurrencies() {
			
		return mcurrency.getAll();
	}

	@Override
	public Currency findCurrencyByIsoCode( String isoCode) {
		log.log(Level.INFO,"Looking up for currency {0}",isoCode);
		Currency currency = mcurrency.getCurrencyByISOCode(isoCode).orElse(null);
		if(currency==null) {
			log.log(Level.WARNING,"Currency not found {0}",isoCode);
		}
			
		return currency;
	}



	@Override
	public Exchange convertToCurrency(Currency currencyFrom, Currency currencyTo, BigDecimal amt,Integer scale) throws CurrencyExchangeException {
		
		return convertToCurrencyByIsoCode(currencyFrom.getIsoCode(), currencyTo.getIsoCode(), amt,scale);
		
	}

	@Override
	public Exchange convertToCurrencyByIsoCode(String currencyFrom, String currencyTo, BigDecimal amt,Integer scale) throws CurrencyExchangeException {
		BigDecimal amtResult=new BigDecimal(0);
		ConversionRate rate=convRate.getConversionRateByIsoCode(currencyFrom, currencyTo).orElse(null);
		if(rate==null) {
			log.log(Level.WARNING,"Conversion rate not found, executing the updater manually ");
			exUpdater.scheduledTimeoutUpdater();
			rate=convRate.getConversionRateByIsoCode(currencyFrom,currencyTo).orElse(null);
		}
		
		if(rate!=null) {
			log.log(Level.INFO,"Caculate conversion for currency from: {0} to: {1} amt: {2}  scale: {3} rate:{4}",new Object[] {currencyFrom,currencyTo,amt,scale,rate.getMultiplyRate()});

			amtResult=amt.multiply(rate.getMultiplyRate()).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
			
		}
		else {
			throw new CurrencyExchangeException("Conversion rate not found");
		}
		
		Exchange exchange = new Exchange(currencyFrom, currencyTo,amt,amtResult.setScale((scale!=null)?scale:DEFAULT_SCALE,RoundingMode.HALF_UP ),rate.getValidFrom());
		
		return exchange;
	}

	@Override
	public void updateRates() throws CurrencyExchangeException {
		log.log(Level.INFO,"Calling updater");
		exUpdater.scheduledTimeoutUpdater();
		
	}
	
	
	




}
