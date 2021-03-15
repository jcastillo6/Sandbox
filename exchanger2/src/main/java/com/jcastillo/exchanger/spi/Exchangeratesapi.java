package com.jcastillo.exchanger.spi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Webservice client for https://api.exchangeratesapi.io
 * @author Jorge Castillo
 *
 */
@Component("Exchangeratesapi")
public class Exchangeratesapi implements ExchangeProvider {
	private static final String BASE_URL="https://api.exchangeratesapi.io";
	private static final String LATEST_PATH="/latest";
	private static final String QUERY_PARAM_NAME= "base";
	private static final Logger log = Logger.getLogger(Exchangeratesapi.class.getName());

	
	@Override
	public List<ExchangeRate> getRatesByBaseCurrency(RestTemplate client, String isoBase) {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL).path(LATEST_PATH);
		builder.queryParam(QUERY_PARAM_NAME, isoBase);
		ExchangerapiMsg msg = client.getForObject(builder.toUriString(), ExchangerapiMsg.class);
		List<ExchangeRate> rates =null;
		if(msg!=null) {
			rates=transformToRateMsg(msg);
			
		}
		else {
			log.log(Level.WARNING,"Not rates found for {0}",isoBase);
		}
				
		return rates;
	}

	@Override
	public List<ExchangeRate> getLatest(RestTemplate client) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL).path(LATEST_PATH);
		ExchangerapiMsg msg = client.getForObject(builder.toUriString(), ExchangerapiMsg.class);
		List<ExchangeRate> rates = null;
		
		if(msg!=null) {
			rates=transformToRateMsg(msg);
			
		}
		else {
			log.log(Level.WARNING,"Not rates found for {0}","lastest");
		}
				
		return rates;
	}

	



	/**
	 * Transform the webservice response into a List<ExchangeRate>
	 * @param msg
	 * @return List<ExchangeRate>
	 */
	private List<ExchangeRate> transformToRateMsg(ExchangerapiMsg msg) {
		if(msg==null)
			throw new IllegalArgumentException("Exchangeratesapi: Invalid ExchangerapiMsg");

		List<ExchangeRate> rates = new ArrayList<>();
		if(msg.getRates().getAUD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"AUD",new BigDecimal(msg.getRates().getAUD())));
		if(msg.getRates().getBGN()>0)
			rates.add(new ExchangeRate(msg.getBase(),"BGN",new BigDecimal(msg.getRates().getBGN())));
		if(msg.getRates().getAUD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"BRL",new BigDecimal(msg.getRates().getAUD())));
		if(msg.getRates().getCAD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CAD",new BigDecimal(msg.getRates().getCAD())));
		if(msg.getRates().getCHF()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CHF",new BigDecimal(msg.getRates().getCHF())));
		if(msg.getRates().getCNY()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CNY",new BigDecimal(msg.getRates().getCNY())));
		if(msg.getRates().getCZK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CZK",new BigDecimal(msg.getRates().getCZK())));
		if(msg.getRates().getDKK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"DKK",new BigDecimal(msg.getRates().getDKK())));
		if(msg.getRates().getGBP()>0)
			rates.add(new ExchangeRate(msg.getBase(),"GBP",new BigDecimal(msg.getRates().getGBP())));
		if(msg.getRates().getHKD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HKD",new BigDecimal(msg.getRates().getHKD())));
		if(msg.getRates().getHRK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HRK",new BigDecimal(msg.getRates().getHRK())));
		if(msg.getRates().getHUF()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HUF",new BigDecimal(msg.getRates().getHUF())));
		if(msg.getRates().getIDR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"IDR",new BigDecimal(msg.getRates().getIDR())));
		if(msg.getRates().getILS()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ILS",new BigDecimal(msg.getRates().getILS())));
		if(msg.getRates().getINR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"INR",new BigDecimal(msg.getRates().getINR())));
		if(msg.getRates().getISK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ISK",new BigDecimal(msg.getRates().getISK())));
		if(msg.getRates().getJPY()>0)
			rates.add(new ExchangeRate(msg.getBase(),"JPY",new BigDecimal(msg.getRates().getJPY())));
		if(msg.getRates().getKRW()>0)	
			rates.add(new ExchangeRate(msg.getBase(),"KRW",new BigDecimal(msg.getRates().getKRW())));
		if(msg.getRates().getMXN()>0)
			rates.add(new ExchangeRate(msg.getBase(),"MXN",new BigDecimal(msg.getRates().getMXN())));
		if(msg.getRates().getMYR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"MYR",new BigDecimal(msg.getRates().getMYR())));
		if(msg.getRates().getNOK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"NOK",new BigDecimal(msg.getRates().getNOK())));
		if(msg.getRates().getNZD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"NZD",new BigDecimal(msg.getRates().getNZD())));
		if(msg.getRates().getPHP()>0)
			rates.add(new ExchangeRate(msg.getBase(),"PHP",new BigDecimal(msg.getRates().getPHP())));
		if(msg.getRates().getPLN()>0) 
			rates.add(new ExchangeRate(msg.getBase(),"PLN",new BigDecimal(msg.getRates().getPLN())));
		if(msg.getRates().getRON()>0)
			rates.add(new ExchangeRate(msg.getBase(),"RON",new BigDecimal(msg.getRates().getRON())));
		if(msg.getRates().getRUB()>0)
			rates.add(new ExchangeRate(msg.getBase(),"RUB",new BigDecimal(msg.getRates().getRUB())));
		if(msg.getRates().getSEK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"SEK",new BigDecimal(msg.getRates().getSEK())));
		if(msg.getRates().getSGD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"SGD",new BigDecimal(msg.getRates().getSGD())));
		if(msg.getRates().getTHB()>0)
			rates.add(new ExchangeRate(msg.getBase(),"THB",new BigDecimal(msg.getRates().getTHB())));
		if(msg.getRates().getUSD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"USD",new BigDecimal(msg.getRates().getUSD())));
		if(msg.getRates().getZAR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ZAR",new BigDecimal(msg.getRates().getZAR())));
		if(msg.getRates().getEUR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"EUR",new BigDecimal(msg.getRates().getEUR())));
			

		return rates;
	}



}
