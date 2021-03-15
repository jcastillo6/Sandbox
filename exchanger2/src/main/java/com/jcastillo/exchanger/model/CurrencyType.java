package com.jcastillo.exchanger.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entity implementation class for Entity: CurrencyType
 *
 */
@Entity
@Table(name = "c_currencytype")
@XmlRootElement
@NamedQueries({
	@NamedQuery (name = CurrencyType.FIND_ALL,query = "SELECT c FROM CurrencyType c " )
	}
)
public class CurrencyType implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "CurrencyType.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="c_currencyType_id")
	private Long id;
	@Version
	private Integer version; 
	@Min(1)
	@Column(nullable = false,unique = true,length = 100)
	private String name;

	public CurrencyType() {
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
		if(!(o instanceof CurrencyType)) {
			return false;
		}
		CurrencyType currencyType = (CurrencyType) o;
		return Objects.equals(this.id, currencyType.getId())&&Objects.equals(this.name, currencyType.name);
		
	}
	
	@Override
	public int hashCode() {
		return version;
	}
	
	@Override
	public String toString() {
		return "CurrencyType{id="+this.id+", name="+this.name+"}";
		
	}
	
}
