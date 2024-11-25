package com.rates.ratesproject.controller;

import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RatesController {
    private RateService rateService;

    @Autowired
    public RatesController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/")
    public Map<String, Map<String, Double>> getRates(){

        HashMap<String, Double> countryStandard = rateService.countryStandardRates();
        HashMap<String, Double> countryReduced = rateService.countryReducedRates();

        Map<String, Double> sortDesc = rateService.sortDesc(countryStandard);
        Map<String, Double> sortAsc = rateService.sortAsc(countryReduced);

        Map<String, Map<String, Double>> result = new HashMap<>();
        result.put("standard_rate:", rateService.printResult(sortDesc));
        result.put("reduced_rate:", rateService.printResult(sortAsc));

        return result;
    }

    @GetMapping("/{id}")
    public CountryRate getSingleRate(@PathVariable("id") String id){
        return rateService.getSingleCountryRate(id);
    }
}
