package com.rates.ratesproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class TaxRate {
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("disclaimer")
    private String disclaimer;
    @JsonProperty("rates")
    private Map<String, CountryRate> rates;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, CountryRate> getRates() {
        return rates;
    }

    public void setRates(Map<String, CountryRate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "TaxRate{" +
                "rates=" + rates +
                '}';
    }
}
