package com.jcastillo.exchanger.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

public class ClientAuthenticationFilter implements ClientRequestFilter {
	private static final Logger log=Logger.getLogger(ClientAuthenticationFilter.class.getName()); 
	private final String user;
	private final String password;
	
	
	

	public ClientAuthenticationFilter(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}




	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headers = requestContext.getHeaders();
		final String basicAuthentication=getBasicAuthentication();
		log.log(Level.INFO,"basic {0}",basicAuthentication);
		headers.add("Authorization", basicAuthentication);
				
	}




	private String getBasicAuthentication() {
		String token = this.user +":"+this.password;
		try {
			byte[] encoded = Base64.getEncoder().encode(token.getBytes("UTF-8"));
			return "Basic "+new String(encoded);
		}catch(UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Cannot encoded with utf-8",e);
		}
		

	}

}
