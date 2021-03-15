package com.jcastillo.exchanger.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.jcastillo.exchanger.model.ConversionRate;

public interface ConversionRateRepo extends JpaRepository<ConversionRate, Integer> {
	@Transactional
	@Modifying
	public int invalidateAll();
	public ConversionRate findConvRateByCurrencies(String isoCodeFrom,String isoCodeTo );
	

}
