package com.jcastillo.exchanger.api;

public class ExchangeNotFoundException extends RuntimeException {
	
	ExchangeNotFoundException(String currencyFrom,String currencyTo){
		super("Currency exchange not found currency from "+currencyFrom+" currency to "+currencyTo);
	}

}
