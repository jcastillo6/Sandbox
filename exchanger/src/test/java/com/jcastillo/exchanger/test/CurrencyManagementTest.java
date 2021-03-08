package com.jcastillo.exchanger.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jcastillo.exchanger.controller.CurrencyExchangeException;
import com.jcastillo.exchanger.controller.CurrencyManagement;
import com.jcastillo.exchanger.data.Currency;
import com.jcastillo.exchanger.process.ExchangeUpdater;

@RunWith(Arquillian.class)
public class CurrencyManagementTest {
	private static final Logger log = Logger.getLogger(CurrencyManagementTest.class.getName());
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war")
        	.addPackages(true, "com.jcastillo")
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	        // Deploy our test datasource
	        .addAsWebInfResource("test-ds.xml");
    }
    
    @EJB
    CurrencyManagement cm;
    @PersistenceContext
    EntityManager em;
    @Inject
    UserTransaction trx;
    @Inject
    ExchangeUpdater xchUpdater;
    
	@Before
	public void setUp() throws Exception {
		log.log(Level.INFO,"Loading data...");
		xchUpdater.scheduledTimeoutUpdater();
		
	}
    
 


	@Test
	public void testFindCurrencies() {
		log.log(Level.INFO,"executing testFindCurrencies");
		List<Currency> currencies= cm.findCurrencies();
		assertTrue(currencies!=null&&currencies.size()>0);
		
	}

	@Test
	public void testFindCurrencyByIsoCode() {
		log.log(Level.INFO,"executing testFindCurrencyByIsoCode");
		Currency currency = cm.findCurrencyByIsoCode("EUR");
		assertTrue(currency!=null);
	}


	@Test
	public void convertToCurrencyByIsoCode() throws CurrencyExchangeException{
		log.log(Level.INFO,"executing convertToCurrencyByIsoCode");
		BigDecimal amt = cm.convertToCurrencyByIsoCode("EUR", "USD", new BigDecimal(2000), 4);
		System.out.printf("Amt %.2f : 2000 Euro to USD \n",amt);
		 amt = cm.convertToCurrencyByIsoCode("USD", "EUR", new BigDecimal(2000), 4);
		System.out.printf("Amt %.2f : 2000 USD to EUR \n",amt);
		 amt = cm.convertToCurrencyByIsoCode("ZAR", "EUR", new BigDecimal(2000), 4);
		System.out.printf("Amt %.2f : 2000 ZAR to EUR \n",amt);
		 amt = cm.convertToCurrencyByIsoCode("EUR", "ZAR", new BigDecimal(2000), 4);
		System.out.printf("Amt %.2f: 2000 EUR to ZAR \n",amt);
		assertTrue(amt.compareTo(new BigDecimal(0))>0);		
		
		
	}
	

}
