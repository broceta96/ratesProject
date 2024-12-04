package com.rates.ratesproject.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class RateImpl implements RateApp{
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private TaxRateRepository taxRateRepository;
    private CountryRateRepository countryRateRepository;

    @Autowired
    public RateImpl(RestTemplate restTemplate, ObjectMapper objectMapper, TaxRateRepository taxRateRepository, CountryRateRepository countryRateRepository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.taxRateRepository = taxRateRepository;
        this.countryRateRepository = countryRateRepository;
    }

    @Override
    public TaxRate readJson(){
        try {
            String url = "https://euvatrates.com/rates.json";
            String jsonData = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(jsonData, TaxRate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void storeData(){
        if(countryRateRepository.count() == 0) {
            TaxRate taxRate = new TaxRate();
            taxRate.setRates(readJson().getRates());
            taxRate.setDisclaimer(readJson().getDisclaimer());
            taxRate.setLastUpdated(readJson().getLastUpdated());

            List<CountryRate> countryRates = new ArrayList<>(taxRate.getRates().values());

            countryRates.forEach((c) -> {
                c.setTaxRate(taxRate);
                countryRateRepository.save(c);
            });
        }
    }

    @Override
    public HashMap<String, Double> populateCountryAndStandardRate(){
        HashMap<String, Double> list = new HashMap<>();
        readJson().getRates().forEach((s, rates) -> {
            list.put(rates.getCountry(), rates.getStandardRate());
        });
        return list;
    }


    @Override
    public HashMap<String, Double> populateCountryAndReducedRate(){
        HashMap<String, Double> list = new HashMap<>();
        readJson().getRates().forEach((s, rates) -> {
            JsonNode reducedRate = rates.getReducedRateAsJsonNode();
            Double reduced = null;

            if(reducedRate != null && reducedRate.isDouble()){
                reduced = reducedRate.asDouble();
            }else{
                reduced = Double.MAX_VALUE;
            }
            list.put(rates.getCountry(), reduced);
        });
        return list;
    }

    @Override
    public Map<String, Double> sortDesc(HashMap<String, Double> country){
        List<Map.Entry<String, Double>> list = new ArrayList<>(country.entrySet());
        list.sort((ratesOne, rateTwo) -> rateTwo.getValue().compareTo(ratesOne.getValue()));

        Map<String, Double> sortDesc = new LinkedHashMap<>();

        for(Map.Entry<String, Double> entry : list){
            sortDesc.put(entry.getKey(), entry.getValue());
        }

        return sortDesc;
    }

    @Override
    public Map<String, Double> sortAsc(HashMap<String, Double> country){
        List<Map.Entry<String, Double>> list = new ArrayList<>(country.entrySet());
        list.sort((ratesOne, ratesTwo) -> ratesOne.getValue().compareTo(ratesTwo.getValue()));

        Map<String, Double> sortAsc = new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry : list){
            sortAsc.put(entry.getKey(), entry.getValue());
        }

        return sortAsc;
    }

    @Override
    public LinkedHashMap<String, Double> printResult(Map<String, Double> sorted){
        LinkedHashMap<String, Double> p = new LinkedHashMap<>();
        sorted.entrySet().stream().limit(3).forEach((rates) -> {
            p.put(rates.getKey(), rates.getValue());
        });
        return p;
    }

    @Override
    public CountryRate getSingleCountryRate(String id){
        return readJson().getRates().get(id.toUpperCase());
    }
}
