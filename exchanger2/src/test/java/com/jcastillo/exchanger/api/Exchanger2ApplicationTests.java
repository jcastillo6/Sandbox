package com.jcastillo.exchanger.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.util.UriComponentsBuilder;

import com.jcastillo.exchanger.controller.Exchange;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Exchanger2ApplicationTests {
	private static final Logger log = Logger.getLogger(Exchanger2ApplicationTests.class.getName());
	
	private static final String BASE_URL="http://localhost";
	private static final String EXCHANGE_PATH="/api/v1/exchanges/exchange";
	private static final String USER="userapi";
	private static final String PASSWORD="userapi123";
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	void successRequest1() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL+":"+port).path(EXCHANGE_PATH)
		.queryParam("currencyfrom", "EUR")
		.queryParam("currencyto", "USD")
		.queryParam("amount", "1");
        HttpHeaders httpHeaders = createHeaders(USER,PASSWORD);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		
		ResponseEntity<Exchange> respExchange =null;
		Exchange exchange = null;
		respExchange=restTemplate.exchange(builder.toUriString(), HttpMethod.GET,httpEntity, Exchange.class);
		if(respExchange.hasBody()) {
			exchange=respExchange.getBody();
		}
		else {
			log.log(Level.SEVERE,"NOT FOUND");
		}
		log.log(Level.INFO,"Success Request 1 result {0}",exchange.getResult());
		assertThat(exchange.getResult().compareTo(BigDecimal.ZERO)>0);
	}
	
	
	@Test
	void successRequest2() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL+":"+port).path(EXCHANGE_PATH)
		.queryParam("currencyfrom", "ZAR")
		.queryParam("currencyto", "USD")
		.queryParam("amount", "1");
        HttpHeaders httpHeaders = createHeaders(USER,PASSWORD);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		
		ResponseEntity<Exchange> respExchange =null;
		Exchange exchange = null;
		respExchange=restTemplate.exchange(builder.toUriString(), HttpMethod.GET,httpEntity, Exchange.class);
		if(respExchange.hasBody()) {
			exchange=respExchange.getBody();
		}
		else {
			log.log(Level.SEVERE,"NOT FOUND");
		}
		log.log(Level.INFO,"Success Request 2 result {0}",exchange.getResult());
		assertThat(exchange.getResult().compareTo(BigDecimal.ZERO)>0);
	}
	
	
	@Test
	void NotFountRequest3() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL+":"+port).path(EXCHANGE_PATH)
		.queryParam("currencyfrom", "ZAR")
		.queryParam("currencyto", "BTC")
		.queryParam("amount", "1");
        HttpHeaders httpHeaders = createHeaders(USER,PASSWORD);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		
		ResponseEntity<Exchange> respExchange =null;
		respExchange=restTemplate.exchange(builder.toUriString(), HttpMethod.GET,httpEntity, Exchange.class);

		log.log(Level.INFO,"Success Request 3 result {0}",respExchange.getStatusCode());
		assertThat(respExchange.getStatusCodeValue()==404);
	}
	
	
	
	
	
	
	HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encode( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}

}
