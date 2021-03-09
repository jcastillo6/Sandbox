package com.jcastillo.exchanger.rest;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.hibernate.validator.constraints.NotEmpty;

import com.jcastillo.exchanger.controller.CurrencyExchangeException;
import com.jcastillo.exchanger.controller.CurrencyManagementLocal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Services for exchanging
 * @author Jorge Castillo
 *
 */
@Path("v1/exchanges")
@Api(value = "Rest Service", description = "Exchanges Services")
@RequestScoped
public class ExchangeService {
	private static final Logger log = Logger.getLogger(ExchangeService.class.getName());
	private static final Integer DEFAULT_SCALE = 4;
	private static final String NOT_FOUND = "Conversion rate doesnt exist";
	private static final String UPDATED_RATES = " Rates updated";
	// the default cache time for https://api.exchangeratesapi.io/latest is 604800, i used the half
	private static final Integer CACHE_TIME = 604800/2;
	@Inject
	private CurrencyManagementLocal currencyManagement;
	private final ExecutorService exServ = Executors.newCachedThreadPool();
	
	
	@ApiOperation(value = "Exchange",notes ="Currency convertor, you need to pass three input fields, currency from, currency to and the monetary value" ,response=Exchange.class )
	@ApiResponses(value= {@ApiResponse(code = 200,message="successfull operation"),@ApiResponse(code = 404,message="Currency convert rate not found")})
	@Path("exchange")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response exchangeRate(@NotEmpty(message = "Invalid currency from ") @QueryParam("currencyfrom") String currencyFrom ,
			@NotEmpty(message = "Invalid currency to ") @QueryParam("currencyto") String currencyTo,
			@NotNull @Min(message = "the amount must be greater than 0",value = 0) @QueryParam("amount") BigDecimal amt  ) {
		
		log.log(Level.INFO,"Entering exchangeRate currency from {0} currency to {1} amt to exchage {3} ",new Object[]{currencyFrom,currencyTo,amt});
		BigDecimal converted=null;
		Exchange ex= null;
		try {
			converted=currencyManagement.convertToCurrencyByIsoCode(currencyFrom, currencyTo, amt, DEFAULT_SCALE);
			log.log(Level.INFO,"Rate found");
			ex=new Exchange(currencyFrom,currencyTo,amt,converted);
			

		} catch (CurrencyExchangeException e) {
			log.log(Level.INFO,"Rate not found");
			return Response.status(Response.Status.NOT_FOUND).entity(NOT_FOUND).type(MediaType.TEXT_PLAIN).build();
						
		}
				 
		CacheControl cc = new CacheControl();
		cc.setMaxAge(CACHE_TIME);
		cc.setPrivate(true);
		ResponseBuilder rb = Response.ok(ex);
		rb.cacheControl(cc);
				
		return rb.build();
		
		
	}
	
	@Path("updaterates")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public void updateRates(@Suspended final AsyncResponse async ) {
		
		async.setTimeout(10, TimeUnit.SECONDS);
		
		Runnable updaterTask = new Runnable() {
			
			@Override
			public void run() {
				try {
					currencyManagement.updateRates();
				} catch (CurrencyExchangeException e) {
					log.log(Level.SEVERE,"failed to process, update rates");

				}
				
				async.resume(Response.ok().entity(UPDATED_RATES).build());
				
			}
		};
		
		exServ.execute(updaterTask);
		
		
	}
	
	
	
	
	

}
