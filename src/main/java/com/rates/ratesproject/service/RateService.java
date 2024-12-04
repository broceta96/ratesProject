package com.rates.ratesproject.service;

import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;
import com.rates.ratesproject.repository.RateApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RateService {
    private RateApp rateApp;

    @Autowired
    public RateService(RateApp rateApp) {
        this.rateApp = rateApp;
    }

    public TaxRate readJson(){
        return rateApp.readJson();
    }

    public HashMap<String, Double> countryStandardRates(){
        return rateApp.populateCountryAndStandardRate();
    }

    public HashMap<String, Double> countryReducedRates(){
        return rateApp.populateCountryAndReducedRate();
    }

    public Map<String, Double> sortDesc(HashMap<String, Double> country){
        return rateApp.sortDesc(country);
    }

    public Map<String, Double> sortAsc(HashMap<String, Double> country){
        return rateApp.sortAsc(country);
    }

    public LinkedHashMap<String, Double> printResult(Map<String, Double> sorted){
        return rateApp.printResult(sorted);
    }

    public CountryRate getSingleCountryRate(String id){
        return rateApp.getSingleCountryRate(id);
    }

    @Transactional
    public void storeData(){
        rateApp.storeData();
    }
}
