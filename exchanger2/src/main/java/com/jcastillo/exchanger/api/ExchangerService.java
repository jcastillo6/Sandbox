package com.jcastillo.exchanger.api;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.jcastillo.exchanger.controller.CurrencyExchangeException;
import com.jcastillo.exchanger.controller.CurrencyExchanger;
import com.jcastillo.exchanger.controller.Exchange;

@RestController
@RequestMapping("/v1/exchanges")
public class ExchangerService {
	private static final Logger log = Logger.getLogger(ExchangerService.class.getName());
	private static final int DEFAULT_SCALE =4;
	
	
	@Autowired
	private CurrencyExchanger exchanger;
	
	
	@GetMapping(value="exchange",produces = {MediaType.APPLICATION_JSON}  )
	public Exchange getExchange(@NotNull @NotEmpty @RequestParam("currencyfrom") String currencyFrom,
			@NotNull @NotEmpty @RequestParam("currencyto") String currencyTo,@NotNull @Min(0) @RequestParam("amount") BigDecimal amount,WebRequest request) {
		
		log.log(Level.INFO,"Entering exchangeRate currency from {0} currency to {1} amt to exchage {2} ",new Object[]{currencyFrom,currencyTo,amount});
		Exchange exchange=null;

		try {
			exchange=exchanger.convertToCurrencyByIsoCode(currencyFrom, currencyTo, amount, DEFAULT_SCALE);
			log.log(Level.INFO,"Rate found");
			
		} catch (CurrencyExchangeException e) {
			log.log(Level.INFO,"Rate not found");
			throw new ExchangeNotFoundException(currencyFrom,currencyTo);
						
		}
		
		if( request.checkNotModified(exchange.getValidFrom().getTime())) {
			throw new ExchangeNotUpdatedException("Not updated");
		}

						
		return exchange;
		
	}
	
	

}
