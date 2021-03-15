package com.jcastillo.exchanger.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Response message
 * @author Jorge Castillo
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchange implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal amtFrom;
	private BigDecimal result;
	private Date validFrom;
	
	
	public Exchange() {
		super();
	}
	
	public Exchange(String currencyFrom, String currencyTo, BigDecimal amtFrom, BigDecimal result,Date validFrom) {
		super();
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amtFrom = amtFrom;
		this.result = result;
		this.validFrom=validFrom;
	}
	

	

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}
	public BigDecimal getAmtFrom() {
		return amtFrom;
	}
	public void setAmtFrom(BigDecimal amtFrom) {
		this.amtFrom = amtFrom;
	}
	public BigDecimal getResult() {
		return result;
	}
	public void setResult(BigDecimal result) {
		this.result = result;
	}

	
	

}
