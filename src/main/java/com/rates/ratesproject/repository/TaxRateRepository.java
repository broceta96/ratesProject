package com.rates.ratesproject.repository;

import com.rates.ratesproject.entity.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {}
