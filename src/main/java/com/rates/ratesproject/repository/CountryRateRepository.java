package com.rates.ratesproject.repository;

import com.rates.ratesproject.entity.CountryRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRateRepository extends JpaRepository<CountryRate, Long> {}
