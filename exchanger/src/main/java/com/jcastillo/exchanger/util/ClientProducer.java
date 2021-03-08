package com.jcastillo.exchanger.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Producer
 * @author Jorge Castillo
 *
 */
@ApplicationScoped
public class ClientProducer {
	
	@Produces @Default
	private Client produceClient() {
		return ClientBuilder.newClient();
	}

}
