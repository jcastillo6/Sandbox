package com.jcastillo.exchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcastillo.exchanger.model.CurrencyType;

public interface CurrencyTypeRepo extends JpaRepository<CurrencyType, Integer> {

}
