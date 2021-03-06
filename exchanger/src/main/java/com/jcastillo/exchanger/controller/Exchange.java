package com.jcastillo.exchanger.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Response message
 * @author Jorge Castillo
 *
 */
@XmlRootElement
public class Exchange implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String currencyFrom;
	@XmlElement
	private String currencyTo;
	@XmlElement
	private BigDecimal amtFrom;
	@XmlElement
	private BigDecimal result;
	private Date validFrom;
	private Link reverseRate;
	
	
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
	

	
	@XmlElement(name="link")
	@XmlJavaTypeAdapter(XmlAdapter.class)
	public Link getReverseRateLink() {
		reverseRate = Link.fromUri("/exchange?currencyfrom={currencyFrom}&currencyto={currencyto}&amtfrom={result}").rel("exchange").title("reverse").build(new Object[] {currencyTo,currencyFrom,result});
		return reverseRate;
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
