package com.jcastillo.exchanger.data;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Currency
 *
 */
@Entity
@Table(name = "c_currency")
@NamedQueries({
	@NamedQuery(name =Currency.FIND_BY_ISO_CODE,query = "SELECT c FROM Currency c WHERE c.isoCode=:isoCode "),
	@NamedQuery(name =Currency.FIND_ALL,query = "SELECT c FROM Currency c")
})
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ISO_CODE = "Currency.findByIsoCode";
	public static final String FIND_ALL = "Currency.findAll";
	
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
	@NotEmpty @Column(nullable = false,unique = true,length = 100)
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
	
	
	
   
}
