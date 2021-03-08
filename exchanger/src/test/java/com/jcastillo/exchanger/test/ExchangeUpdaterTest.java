package com.jcastillo.exchanger.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jcastillo.exchanger.data.ConversionRate;
import com.jcastillo.exchanger.model.MConversionRate;
import com.jcastillo.exchanger.process.ExchangeUpdater;
@RunWith(Arquillian.class)
public class ExchangeUpdaterTest {
	private static final Logger log = Logger.getLogger(ExchangeUpdaterTest.class.getName());
	
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war")
        		.addPackages(true, "com.jcastillo")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
    	        // Deploy our test datasource
    	        .addAsWebInfResource("test-ds.xml");
    }
    
    @Inject
    ExchangeUpdater xup;
    @Inject
    MConversionRate cnvRate;

	@Before
	public void setUp() throws Exception {
		log.log(Level.INFO,"Loading data...");
		xup.scheduledTimeoutUpdater();
		
	}
	
	@Test
	public void scheduledTimeoutUpdater() {
		List<ConversionRate> rates = cnvRate.getAll();
		assertTrue(rates.size()>0);
		
		
	}
	
	@Test
	public void test() {
		xup.scheduledTimeoutCleaner();
		List<ConversionRate> rates = cnvRate.getAll();
		assertTrue(rates.size()==0);
		
	}
	

}
