package com.rates.ratesproject.repository;

import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface RateApp {
    TaxRate readJson();
    HashMap<String, Double> populateCountryAndStandardRate();
    HashMap<String, Double>  populateCountryAndReducedRate();
    Map<String, Double> sortDesc(HashMap<String, Double> country);
    Map<String, Double> sortAsc(HashMap<String, Double> country);
    LinkedHashMap<String, Double> printResult(Map<String, Double> sorted);
    CountryRate getSingleCountryRate(String id);
    void storeData();
}
