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

    @Autowired
    public RateImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public TaxRate readJson(){
        try {
            String url = "https://euvatrates.com/rates.json";
            String jsonData = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(jsonData, TaxRate.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HashMap<String, Double> populateCountryAndStandardRate(){
        HashMap<String, Double> country = new HashMap<String, Double>();
        readJson().getRates().forEach((s, rates)-> {
            country.put(rates.getCountry(), rates.getStandardRate());
        });
        return country;
    }

    @Override
    public HashMap<String, Double>  populateCountryAndReducedRate(){
        HashMap<String, Double> country = new HashMap<String, Double>();
        readJson().getRates().forEach((s, rates) -> {
            JsonNode reducedRateNode = rates.getReducedRate();
            Double reducedRate = null;

            if(reducedRateNode != null && reducedRateNode.isNumber()) {
                reducedRate = reducedRateNode.asDouble();
            }else {
                reducedRate = Double.MAX_VALUE;
            }
            country.put(rates.getCountry(), reducedRate);
        });
        return country;
    }

    @Override
    public Map<String, Double> sortDesc(HashMap<String, Double> country){
        List<Map.Entry<String, Double>> list = new ArrayList<>(country.entrySet());
        list.sort((entryOne, entryTwo) -> entryTwo.getValue().compareTo(entryOne.getValue()));

        Map<String, Double> sortDesc = new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry : list){
            sortDesc.put(entry.getKey(), entry.getValue());
        }
        return sortDesc;
    }

    @Override
    public Map<String, Double> sortAsc(HashMap<String, Double> country){
        List<Map.Entry<String, Double>> secondList = new ArrayList<>(country.entrySet());
        secondList.sort((entryOne, entryTwo) -> entryOne.getValue().compareTo(entryTwo.getValue()));
        Map<String, Double> sortAsc = new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry : secondList){
            sortAsc.put(entry.getKey(), entry.getValue());
        }
        return sortAsc;
    }

    @Override
    public LinkedHashMap<String, Double> printResult(Map<String, Double> sorted){
        LinkedHashMap<String, Double> rates = new LinkedHashMap<String, Double>();
        sorted.entrySet().stream().limit(3).forEach((e) -> {
            rates.put(e.getKey(), e.getValue());
        });
        return rates;
    }

    @Override
    public CountryRate getSingleCountryRate(String id){
        return readJson().getRates().get(id.toUpperCase());
    }
}
