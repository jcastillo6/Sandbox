package com.jcastillo.exchanger.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jcastillo.exchanger.model.Currency;



public interface CurrencyExchanger {
	/**
	 * Find all currencies in the data store
	 * @return List<Currency> empty if no record is found
	 */
	List<Currency> findCurrencies();
	/**
	 * Look up for the currency base in the iso code
	 * @param isoCode
	 * @return
	 */
	Currency findCurrencyByIsoCode(@NotNull @NotEmpty String isoCode);
	/**
	 * this method looks up for the convertion rates and calculates the result amt
	 * @param currencyFrom iso code
	 * @param currencyTo iso code
	 * @param amt result
	 * @param scale 
	 * @return converted amount Big decimal
	 * @throws CurrencyExchangeException if no currency rate is found
	 */
	Exchange convertToCurrency(@NotNull Currency currencyFrom, @NotNull Currency currencyTo,BigDecimal amt,Integer scale) throws CurrencyExchangeException;
	/**
	 * this method looks up for the convertion rates and calculates the result amt
	 * @param currencyFrom
	 * @param currencyTo
	 * @param amt
	 * @param scale
	 * @return converted amount Big decimal
	 * @throws CurrencyExchangeException CurrencyExchangeException if no currency rate is found
	 */
	Exchange convertToCurrencyByIsoCode(@NotNull String currencyFrom,@NotNull String currencyTo, @NotNull BigDecimal amt, Integer scale) throws CurrencyExchangeException;
	
	/**
	 * Call the execution of the background process that update the rates
	 * @throws CurrencyExchangeException
	 */
	void updateRates() throws CurrencyExchangeException;

}
