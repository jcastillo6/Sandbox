package com.jcastillo.exchanger.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * Entity implementation class for Entity: Currency
 *
 */
@Entity
@Table(name = "c_currency")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name =Currency.FIND_BY_ISO_CODE,query = "SELECT c FROM Currency c WHERE c.isoCode=:isoCode "),
	@NamedQuery (name = Currency.FIND_ALL_MISSING_RATES,query= "SELECT c FROM Currency c WHERE c.id NOT IN (SELECT r.currencyFrom.id FROM ConversionRate r WHERE r.validTo IS NULL) ")

})
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ISO_CODE = "Currency.findByIsoCode";
	public static final String FIND_ALL_MISSING_RATES = "Currency.findMissingRates";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_currency_id")
	private Long id;
	@Version
	private Integer version;
	@Column(nullable = false,unique = true,length = 4)
	private String isoCode;
	@Column(nullable = false,unique = true,length = 4)
	private String curSymbol;
	@Column(nullable = false,unique = true,length = 100)
	private String name;
	@OneToMany(fetch = FetchType.LAZY,        
			mappedBy = "currencyTo",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ConversionRate> convertRateTo;
	@OneToMany(fetch = FetchType.LAZY,        
			mappedBy = "currencyFrom",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ConversionRate> convertRateFrom;
	
	
	public Currency() {
		super();
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

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getCurSymbol() {
		return curSymbol;
	}

	public void setCurSymbol(String curSymbol) {
		this.curSymbol = curSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this ==o) {
			return true;
			
		}
		if(!(o instanceof Currency)) {
			return false;
		}
		Currency currency = (Currency) o;
		return Objects.equals(this.id, currency.getId())&&Objects.equals(this.isoCode, currency.isoCode);
		
	}
	
	@Override
	public String toString() {
		return "Currency{id="+this.getId()+",curSymbol="+this.curSymbol+",isoCode="+this.isoCode+"}";
		
		
	}
	
	@Override
	public int hashCode() {
		return version;
	}
	
   
}
