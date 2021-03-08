package com.jcastillo.exchanger.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
/**
 * Rest config
 * @author Jorge Castillo
 *
 */
@ApplicationPath("/api")
public class RestAppConfig extends Application {
	private static final Logger log = Logger.getLogger(RestAppConfig.class.getName());
    

    
    public RestAppConfig() {
    	log.log(Level.INFO, " Application Rest Config" );
    	init();
    	
    	
    }
    
    private void init() {
    	log.log(Level.INFO, " Swagger config" );
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/exchanger/api");
        beanConfig.setResourcePackage("com.jcastillo");
        beanConfig.setTitle("Exchanger");
        beanConfig.setDescription("Services descriptor");
        beanConfig.setScan(true);
		
	}


}
