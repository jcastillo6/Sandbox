package com.jcastillo.exchanger.spi;

import java.math.BigDecimal;

/**
 * Pojo class, helper for the exchangers services
 * @author Jorge Castillo
 *
 */
public class ExchangeRate {
	private String isoCodeFrom;
	private String isoCodeTo;
	private BigDecimal rate;
	
	
	public ExchangeRate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ExchangeRate(String isoCodeFrom, String isoCodeTo, BigDecimal rate) {
		super();
		this.isoCodeFrom = isoCodeFrom;
		this.isoCodeTo = isoCodeTo;
		this.rate = rate;
	}


	public String getIsoCodeFrom() {
		return isoCodeFrom;
	}
	public void setIsoCodeFrom(String isoCodeFrom) {
		this.isoCodeFrom = isoCodeFrom;
	}
	public String getIsoCodeTo() {
		return isoCodeTo;
	}
	public void setIsoCodeTo(String isoCodeTo) {
		this.isoCodeTo = isoCodeTo;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	

}
