package com.jcastillo.exchanger.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcastillo.exchanger.controller.process.UpdaterServiceProvider;
import com.jcastillo.exchanger.model.ConversionRate;
import com.jcastillo.exchanger.model.Currency;
import com.jcastillo.exchanger.repository.ConversionRateRepo;
import com.jcastillo.exchanger.repository.CurrencyRepo;

@Component
public class CurrencyExchangeImpl implements CurrencyExchanger{
	private static final Logger log = Logger.getLogger(CurrencyExchangeImpl.class.getName());
	private final int DEFAULT_SCALE =4;
	@Autowired
	ConversionRateRepo convRateRepo;
	@Autowired
	UpdaterServiceProvider updaterRates;
	@Autowired
	CurrencyRepo currencyRepo;

	@Override
	public List<Currency> findCurrencies() {
		
		return null;
	}

	@Override
	public Currency findCurrencyByIsoCode(@NotNull @NotEmpty String isoCode) {
		
		return currencyRepo.findByIsoCode(isoCode);
	}

	@Override
	public Exchange convertToCurrency(@NotNull Currency currencyFrom, @NotNull Currency currencyTo, BigDecimal amt,
			Integer scale) throws CurrencyExchangeException {
		
		
		return convertToCurrencyByIsoCode(currencyFrom.getCurSymbol(),currencyTo.getCurSymbol(),amt,scale);
	}

	@Override
	public Exchange convertToCurrencyByIsoCode(@NotNull String currencyFrom, @NotNull String currencyTo,
			@NotNull BigDecimal amt, Integer scale) throws CurrencyExchangeException {
		log.log(Level.INFO,"convertToCurrencyByIsoCode {0} {1} {2} {3}",new Object[] {currencyFrom,currencyTo,amt,scale});
		ConversionRate convRate=convRateRepo.findConvRateByCurrencies(currencyFrom, currencyTo);
		Exchange exchange = null;
		if(convRate==null) {
			updaterRates.updateRates();
			convRate=convRateRepo.findConvRateByCurrencies(currencyFrom, currencyTo);
			if(convRate==null) {
				throw new CurrencyExchangeException("Not found exchange rate");
			}
		
		}
		
		exchange = new Exchange();
		exchange.setAmtFrom(amt);
		exchange.setCurrencyFrom(currencyFrom);
		exchange.setCurrencyTo(currencyTo);
		exchange.setResult(amt.multiply(convRate.getMultiplyRate()).setScale(scale!=null?scale:DEFAULT_SCALE,RoundingMode.HALF_UP));
		exchange.setValidFrom(convRate.getValidFrom());
		
		return exchange;
	}

	@Override
	public void updateRates() throws CurrencyExchangeException {
		updaterRates.updateRates();
		
	}
	

	
	
}
