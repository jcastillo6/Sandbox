package com.jcastillo.exchanger.controller.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * Producer
 * @author Jorge Castillo
 *
 */
@Configuration
public class ClientProducer {
	
	/*@Bean 
	public RestTemplate produceClient(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMinutes(1)).setReadTimeout(Duration.ofMinutes(1)).build();
	}*/
	@Bean
	public RestTemplate restTemplate() {
	 
	    var factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(3000);
	    factory.setReadTimeout(3000);
	    return new RestTemplate(factory);
	}
	

}
