package com.jcastillo.exchanger.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jcastillo.exchanger.data.Currency;

/**
 * Local bean interface, this defines the method offered to third parties app
 * @author Jorge Castillo
 *
 */
@Local
public interface CurrencyManagementLocal {
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
	BigDecimal convertToCurrency(@NotNull Currency currencyFrom, @NotNull Currency currencyTo,BigDecimal amt,Integer scale) throws CurrencyExchangeException;
	/**
	 * this method looks up for the convertion rates and calculates the result amt
	 * @param currencyFrom
	 * @param currencyTo
	 * @param amt
	 * @param scale
	 * @return converted amount Big decimal
	 * @throws CurrencyExchangeException CurrencyExchangeException if no currency rate is found
	 */
	BigDecimal convertToCurrencyByIsoCode(@NotNull String currencyFrom,@NotNull String currencyTo, @NotNull BigDecimal amt, Integer scale) throws CurrencyExchangeException;	
}
