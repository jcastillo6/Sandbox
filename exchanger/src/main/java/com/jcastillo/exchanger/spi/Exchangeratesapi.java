package com.jcastillo.exchanger.spi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

/**
 * Webservice client for https://api.exchangeratesapi.io
 * @author Jorge Castillo
 *
 */
@Default
public class Exchangeratesapi implements Exchanger {
	private static final String BASE_URL="https://api.exchangeratesapi.io";
	private static final String LATEST_PATH="latest";
	private static final String QUERY_PARAM_NAME= "base";
	private static final int TIME_OUT=200;
	private static final Logger log = Logger.getLogger(Exchangeratesapi.class.getName());

	
	@Override
	public List<ExchangeRate> getRatesByBaseCurrency(Client client, String isoBase) {
		ExchangerapiMsg msg = client.property("connection.timeout", TIME_OUT).target(BASE_URL).path(LATEST_PATH).queryParam(QUERY_PARAM_NAME, isoBase).request(MediaType.APPLICATION_JSON).get(ExchangerapiMsg.class);
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
	public List<ExchangeRate> getLatest(Client client) {
		ExchangerapiMsg msg = client.property("connection.timeout", TIME_OUT).target(BASE_URL).path(LATEST_PATH).request(MediaType.APPLICATION_JSON).get(ExchangerapiMsg.class);
		List<ExchangeRate> rates =null;
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
		if(msg.getRate().getAUD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"AUD",new BigDecimal(msg.getRate().getAUD())));
		if(msg.getRate().getBGN()>0)
			rates.add(new ExchangeRate(msg.getBase(),"BGN",new BigDecimal(msg.getRate().getBGN())));
		if(msg.getRate().getAUD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"BRL",new BigDecimal(msg.getRate().getAUD())));
		if(msg.getRate().getCAD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CAD",new BigDecimal(msg.getRate().getCAD())));
		if(msg.getRate().getCHF()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CHF",new BigDecimal(msg.getRate().getCHF())));
		if(msg.getRate().getCNY()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CNY",new BigDecimal(msg.getRate().getCNY())));
		if(msg.getRate().getCZK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"CZK",new BigDecimal(msg.getRate().getCZK())));
		if(msg.getRate().getDKK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"DKK",new BigDecimal(msg.getRate().getDKK())));
		if(msg.getRate().getGBP()>0)
			rates.add(new ExchangeRate(msg.getBase(),"GBP",new BigDecimal(msg.getRate().getGBP())));
		if(msg.getRate().getHKD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HKD",new BigDecimal(msg.getRate().getHKD())));
		if(msg.getRate().getHRK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HRK",new BigDecimal(msg.getRate().getHRK())));
		if(msg.getRate().getHUF()>0)
			rates.add(new ExchangeRate(msg.getBase(),"HUF",new BigDecimal(msg.getRate().getHUF())));
		if(msg.getRate().getIDR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"IDR",new BigDecimal(msg.getRate().getIDR())));
		if(msg.getRate().getILS()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ILS",new BigDecimal(msg.getRate().getILS())));
		if(msg.getRate().getINR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"INR",new BigDecimal(msg.getRate().getINR())));
		if(msg.getRate().getISK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ISK",new BigDecimal(msg.getRate().getISK())));
		if(msg.getRate().getJPY()>0)
			rates.add(new ExchangeRate(msg.getBase(),"JPY",new BigDecimal(msg.getRate().getJPY())));
		if(msg.getRate().getKRW()>0)	
			rates.add(new ExchangeRate(msg.getBase(),"KRW",new BigDecimal(msg.getRate().getKRW())));
		if(msg.getRate().getMXN()>0)
			rates.add(new ExchangeRate(msg.getBase(),"MXN",new BigDecimal(msg.getRate().getMXN())));
		if(msg.getRate().getMYR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"MYR",new BigDecimal(msg.getRate().getMYR())));
		if(msg.getRate().getNOK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"NOK",new BigDecimal(msg.getRate().getNOK())));
		if(msg.getRate().getNZD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"NZD",new BigDecimal(msg.getRate().getNZD())));
		if(msg.getRate().getPHP()>0)
			rates.add(new ExchangeRate(msg.getBase(),"PHP",new BigDecimal(msg.getRate().getPHP())));
		if(msg.getRate().getPLN()>0) 
			rates.add(new ExchangeRate(msg.getBase(),"PLN",new BigDecimal(msg.getRate().getPLN())));
		if(msg.getRate().getRON()>0)
			rates.add(new ExchangeRate(msg.getBase(),"RON",new BigDecimal(msg.getRate().getRON())));
		if(msg.getRate().getRUB()>0)
			rates.add(new ExchangeRate(msg.getBase(),"RUB",new BigDecimal(msg.getRate().getRUB())));
		if(msg.getRate().getSEK()>0)
			rates.add(new ExchangeRate(msg.getBase(),"SEK",new BigDecimal(msg.getRate().getSEK())));
		if(msg.getRate().getSGD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"SGD",new BigDecimal(msg.getRate().getSGD())));
		if(msg.getRate().getTHB()>0)
			rates.add(new ExchangeRate(msg.getBase(),"THB",new BigDecimal(msg.getRate().getTHB())));
		if(msg.getRate().getUSD()>0)
			rates.add(new ExchangeRate(msg.getBase(),"USD",new BigDecimal(msg.getRate().getUSD())));
		if(msg.getRate().getZAR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"ZAR",new BigDecimal(msg.getRate().getZAR())));
		if(msg.getRate().getEUR()>0)
			rates.add(new ExchangeRate(msg.getBase(),"EUR",new BigDecimal(msg.getRate().getEUR())));
			

		return rates;
	}



}
