package com.rates.ratesproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "tax_rate")
public class TaxRate { // FIXME: Same comment from CountryRate

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // FIXME: use UUID

    @JsonProperty("last_updated") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String lastUpdated;

    @JsonProperty("disclaimer") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String disclaimer;

    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL)
    @JsonProperty("rates") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
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
