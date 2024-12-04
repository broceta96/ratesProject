package com.rates.ratesproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "tax_rate")
public class TaxRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("disclaimer")
    private String disclaimer;

    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL)
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
