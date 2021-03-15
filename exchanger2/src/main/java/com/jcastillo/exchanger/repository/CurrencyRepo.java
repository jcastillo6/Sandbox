package com.jcastillo.exchanger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.exchanger.model.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
	
	public Currency findByIsoCode(String isoCode);
	public List<Currency> findMissingRates();

	

}
