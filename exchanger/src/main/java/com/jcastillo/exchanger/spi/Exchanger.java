package com.jcastillo.exchanger.spi;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Definition of the methods provided by the service providers
 * @author Jorge Castillo
 *
 */
public interface Exchanger {
	
	List<ExchangeRate> getRatesByBaseCurrency(@NotNull Client client,@NotNull @NotEmpty String isoBase) ;
	List<ExchangeRate> getLatest(@NotNull Client client) ;

}
