package com.rates.ratesproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rates.ratesproject.entity.CountryRate;
import com.rates.ratesproject.entity.TaxRate;
import com.rates.ratesproject.repository.RateApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RateServiceTest {
    @Mock
    private RateApp rateApp;

    @InjectMocks
    private RateService rateService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testReadJson(){
        CountryRate countryRate = new CountryRate();
        countryRate.setCountry("Serbia");
        countryRate.setStandardRate(20.0);
        countryRate.setReducedRateFromJsonNode(objectMapper.valueToTree(8.0));

        Map<String, CountryRate> rates = new HashMap<>();
        rates.put("SRB", countryRate);

        TaxRate expectedTaxRate = new TaxRate();
        expectedTaxRate.setRates(rates);
        when(rateApp.readJson()).thenReturn(expectedTaxRate);

        TaxRate taxRate = rateService.readJson();

        assertEquals(expectedTaxRate, taxRate);
    }

    @Test
    public void testCountryStandardRates(){
        HashMap<String, Double> expectedRates = new HashMap<String, Double>();
        expectedRates.put("SRB", 20.0);
        when(rateApp.populateCountryAndStandardRate()).thenReturn(expectedRates);

        HashMap<String, Double> rates = rateService.countryStandardRates();

        assertEquals(expectedRates, rates);
    }

    @Test
    public void testCountryReducedRates(){
        HashMap<String, Double> expectedRates = new HashMap<String, Double>();
        expectedRates.put("MO", 15.0);
        when(rateApp.populateCountryAndReducedRate()).thenReturn(expectedRates);

        HashMap<String, Double> rates = rateService.countryReducedRates();

        assertEquals(expectedRates, rates);
    }

    @Test
    public void testSortDesc(){
        HashMap<String, Double> country = new HashMap<>();
        country.put("SRB", 20.0);
        country.put("BIH", 25.0);
        country.put("CRO", 19.0);

        Map<String, Double> expectedSort = new LinkedHashMap<>();
        expectedSort.put("BIH", 25.0);
        expectedSort.put("SRB", 20.0);
        expectedSort.put("CRO", 19.0);
        when(rateApp.sortDesc(country)).thenReturn(expectedSort);

        Map<String, Double> sort = rateService.sortDesc(country);

        assertEquals(expectedSort, sort);
    }

    @Test
    public void testSortAsc(){
        HashMap<String, Double> country = new HashMap<>();
        country.put("SRB", 5.0);
        country.put("KIM", 4.0);
        country.put("BIH", 7.0);

        Map<String, Double> expectedSort = new LinkedHashMap<>();
        expectedSort.put("KIM", 4.0);
        expectedSort.put("SRB", 5.0);
        expectedSort.put("BIH", 7.0);
        when(rateApp.sortAsc(country)).thenReturn(expectedSort);

        Map<String, Double> sort = rateService.sortAsc(country);

        assertEquals(expectedSort, sort);
    }

    @Test
    public void testPrintResult(){
        HashMap<String, Double> sorted = new LinkedHashMap<>();
        sorted.put("KIM", 7.0);
        sorted.put("SRB", 5.0);
        sorted.put("BIH", 8.0);
        sorted.put("CRO", 9.0);

        LinkedHashMap<String, Double> expectedPrint = new LinkedHashMap<>();
        expectedPrint.put("SRB", 5.0);
        expectedPrint.put("KIM", 7.0);
        expectedPrint.put("BIH", 8.0);
        when(rateApp.printResult(rateApp.sortAsc(sorted))).thenReturn(expectedPrint);

        LinkedHashMap<String, Double> print = rateService.printResult(rateApp.sortAsc(sorted));

        assertEquals(expectedPrint, print);
    }

    @Test
    public void testSingleCountryRate(){
        String id = "it";
        CountryRate expectedRate = new CountryRate();
        expectedRate.setCountry("Italy");
        expectedRate.setStandardRate(22.0);
        when(rateApp.getSingleCountryRate(id)).thenReturn(expectedRate);

        CountryRate rate = rateService.getSingleCountryRate(id);

        assertEquals(expectedRate, rate);
    }
}
