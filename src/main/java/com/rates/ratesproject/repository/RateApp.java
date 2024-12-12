package com.rates.ratesproject.repository;

import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface RateApp { // FIXME: move this to service package
    TaxRate readJson(); // FIXME: rename to getDataTaxRate or something like that
    HashMap<String, Double> populateCountryAndStandardRate();
    HashMap<String, Double>  populateCountryAndReducedRate();
    Map<String, Double> sortDesc(HashMap<String, Double> country); // FIXME: sortDesc and sortAsc are some method with different sorting param. So, create only one method with sorting param
    Map<String, Double> sortAsc(HashMap<String, Double> country);
    LinkedHashMap<String, Double> printResult(Map<String, Double> sorted); // FIXME: rename this to be understandable because this means that we are only printing result
    CountryRate getSingleCountryRate(String id);
    void storeData(); // FIXME: rename to save or create
}
