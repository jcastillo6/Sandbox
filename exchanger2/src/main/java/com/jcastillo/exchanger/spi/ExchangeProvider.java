package com.jcastillo.exchanger.spi;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.client.RestTemplate;


/**
 * Definition of the methods provided by the service providers
 * @author Jorge Castillo
 *
 */
public interface ExchangeProvider {
	
	List<ExchangeRate> getRatesByBaseCurrency(@NotNull RestTemplate client,@NotNull @NotEmpty String isoBase) ;
	List<ExchangeRate> getLatest(@NotNull RestTemplate client) ;

}
