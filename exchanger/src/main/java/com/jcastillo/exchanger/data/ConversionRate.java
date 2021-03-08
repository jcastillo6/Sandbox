package com.jcastillo.exchanger.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: CurrencyConvert
 *
 */
@Entity
@Table(name = "c_conversionrate")
@NamedQueries(
		
		{
			@NamedQuery (name = ConversionRate.FIND_ALL,query = "SELECT c FROM ConversionRate c " ),
			@NamedQuery (name = ConversionRate.FIND_CONV_RATE_BY_CURRENCY,query = "SELECT c FROM ConversionRate c WHERE c.currencyFrom.isoCode=:isoCodeFrom and c.currencyTo.isoCode=:isoCodeTo and c.validTo IS NULL " ),
			@NamedQuery (name = ConversionRate.INVALIDATE_ALL,query = "UPDATE ConversionRate r SET r.validTo=CURRENT_TIMESTAMP" ),
			@NamedQuery (name = ConversionRate.FIND_ALL_MISSING_RATES,query= "SELECT c FROM Currency c WHERE c.id NOT IN (SELECT r.currencyFrom.id FROM ConversionRate r WHERE r.validTo IS NULL) ")
		}
)
public class ConversionRate implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL ="ConversionRate.findAll";
	public static final String  FIND_CONV_RATE_BY_CURRENCY="CurrencyRate.findConvRateByCurrency" ;
	public static final String INVALIDATE_ALL="CurrencyRate.invalidate";
	public static final String FIND_ALL_MISSING_RATES ="CurrencyRate.missingRates";

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_conversionrate_id")
	private Long id;
	@Version
	private Integer version; 
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date validFrom;
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;
	@NotNull 
	@Column(precision=30, scale=4)
	private BigDecimal multiplyRate;
	@NotNull 
	@Column(precision=30, scale=4)
	private BigDecimal divideRate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="c_currencyto_id", nullable=false)
	private Currency currencyTo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="c_currencyfrom_id", nullable=false)
	private Currency currencyFrom;

	public ConversionRate() {
		super();
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public BigDecimal getMultiplyRate() {
		return multiplyRate;
	}

	public void setMultiplyRate(BigDecimal multiplyRate) {
		this.multiplyRate = multiplyRate;
	}

	public BigDecimal getDivideRate() {
		return divideRate;
	}

	public void setDivideRate(BigDecimal divideRate) {
		this.divideRate = divideRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   
}
