package com.rates.ratesproject.controller;

import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;
import com.rates.ratesproject.repository.CountryRateRepository;
import com.rates.ratesproject.service.RateService;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void loadData(){
        rateService.storeData();
    }

    @GetMapping("/")
    public String getRates(){
        return "Welcome!";
    }

    @GetMapping("/rates")
    public TaxRate showAllRates(){
        return rateService.readJson();
    }

    @GetMapping("/rates/highestStandard")
    public Map<String, Map<String, Double>> getHighest(){
        HashMap<String, Double> country = rateService.countryStandardRates();
        Map<String, Double> sort = rateService.sortDesc(country);

        Map<String, Map<String, Double>> result = new HashMap<>();
        result.put("standard_rates", rateService.printResult(sort));
        return result;
    }

    @GetMapping("/rates/lowestReduced")
    public Map<String, Map<String, Double>> getLowest(){
        HashMap<String, Double> country = rateService.countryReducedRates();
        Map<String, Double> sort = rateService.sortAsc(country);

        Map<String, Map<String, Double>> result = new HashMap<>();
        result.put("reduced_rate", rateService.printResult(sort));
        return result;
    }

    @GetMapping("/rates/{id}")
    public CountryRate getSingleRate(@PathVariable("id") String id){
        return rateService.getSingleCountryRate(id);
    }
}
