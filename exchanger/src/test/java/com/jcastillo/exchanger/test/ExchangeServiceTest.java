package com.jcastillo.exchanger.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class ExchangeServiceTest {
	private static final Logger log = Logger.getLogger(ExchangeServiceTest.class.getName());
	private static final String name = "apiuser";
	private static final String password = "apiuser123";

	@Test
	public void UnauthorizedTest() {
		log.log(Level.INFO, "Unauthorizedtest");
		Response response = ClientBuilder.newClient().target("http://localhost:8080/exchanger")
				.path("/api/v1/exchanges/exchange").queryParam("currencyfrom", "USD").queryParam("currencyto", "EUR")
				.queryParam("amount", 1).request(MediaType.APPLICATION_JSON).get();

		// unauthorized
		assertTrue(response.getStatus() == 401);

	}

	@Test
	public void successfullTest() {
		log.log(Level.INFO, "successfultest");
		ClientAuthenticationFilter af = new ClientAuthenticationFilter(name, password);

		Response response = ClientBuilder.newClient().target("http://localhost:8080/exchanger")
				.path("/api/v1/exchanges/exchange").register(af).queryParam("currencyfrom", "USD")
				.queryParam("currencyto", "EUR").queryParam("amount", 1).request(MediaType.APPLICATION_JSON).get();

		log.log(Level.WARNING, "status {0}", response.getStatus());
		assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());

	}

	@Test
	public void foundRecordTest() {
		log.log(Level.INFO, "successfultest");
		ClientAuthenticationFilter af = new ClientAuthenticationFilter(name, password);

		Response response = ClientBuilder.newClient().target("http://localhost:8080/exchanger")
				.path("/api/v1/exchanges/exchange").register(af).queryParam("currencyfrom", "USD")
				.queryParam("currencyto", "EUR").queryParam("amount", 1).request(MediaType.APPLICATION_JSON).get();

		String result = response.readEntity(String.class);

		log.log(Level.WARNING, "Response {0}", result);
		assertTrue(result.contains("result"));

	}

	@Test
	public void NotRecordFoundTest() {
		log.log(Level.INFO, "NotRecordFoundTest");
		ClientAuthenticationFilter af = new ClientAuthenticationFilter(name, password);

		Response response = ClientBuilder.newClient().target("http://localhost:8080/exchanger")
				.path("/api/v1/exchanges/exchange").register(af).queryParam("currencyfrom", "USD")
				.queryParam("currencyto", "XXX").queryParam("amount", 1).request(MediaType.APPLICATION_JSON).get();

		log.log(Level.WARNING, "Result {0}", response.readEntity(String.class));
		assertTrue(response.getStatus() == 404);

	}

	@Test
	public void updateRatesTest() throws InterruptedException, ExecutionException {
		log.log(Level.INFO, "updateRatesTest");
		Client client = ClientBuilder.newClient();
		WebTarget wt = client.target("http://localhost:8080/exchanger").path("/api/v1/exchanges/updaterates");
		Builder bldr = wt.request(MediaType.TEXT_PLAIN);
		AsyncInvoker invk = bldr.async();
		Future<Response> respFuture = invk.get();
		Response resp = respFuture.get();
		String result = resp.readEntity(String.class);
		log.log(Level.WARNING, "Result -> {0}", result);
		assert (result != null && !result.isEmpty());

	}

}
