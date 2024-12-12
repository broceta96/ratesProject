package com.rates.ratesproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "country_rate")
public class CountryRate { // FIXME: use lombok annotations: @Getter @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // FIXME: use UUID

    private String country;

    @JsonProperty("standard_rate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private double standardRate;

    @JsonProperty("reduced_rate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String reducedRate;

    @JsonProperty("reduced_rate_alt") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String reducedRateAlt;

    @JsonProperty("super_reduced_rate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String superReducedRate;

    @JsonProperty("parking_rate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String parkingRate;

    @JsonProperty("_comment") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String comment;

    @JsonProperty("iso_duplicate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String isoDuplicate;

    @JsonProperty("iso_duplicate_of") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private String isoDuplicateOf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_rate") // FIXME: remove this property and create DTO object, then create mappers and use to map DTOs to Entity. NOTE: use mapstruct
    private TaxRate taxRate;

    public CountryRate(){}

    public CountryRate(String country, double standardRate, String reducedRate, String reducedRateAlt, String superReducedRate, String parkingRate) {
        this.country = country;
        this.standardRate = standardRate;
        this.reducedRate = reducedRate;
        this.reducedRateAlt = reducedRateAlt;
        this.superReducedRate = superReducedRate;
        this.parkingRate = parkingRate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(double standardRate) {
        this.standardRate = standardRate;
    }

    public String getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(String reducedRate) {
        this.reducedRate = reducedRate;
    }

    public String getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(String reducedRateAlt) {
        this.reducedRateAlt = reducedRateAlt;
    }

    public String getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(String superReducedRate) {
        this.superReducedRate = superReducedRate;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsoDuplicate() {
        return isoDuplicate;
    }

    public void setIsoDuplicate(String isoDuplicate) {
        this.isoDuplicate = isoDuplicate;
    }

    public String getIsoDuplicateOf() {
        return isoDuplicateOf;
    }

    public void setIsoDuplicateOf(String isoDuplicateOf) {
        this.isoDuplicateOf = isoDuplicateOf;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public JsonNode getReducedRateAsJsonNode() {
        try {
            return new ObjectMapper().readTree(this.reducedRate);
        } catch (Exception e) {
            throw new RuntimeException("Error converting reducedRate to JsonNode", e);
        }
    }

    public void setReducedRateFromJsonNode(JsonNode reducedRate) {
        try {
            this.reducedRate = reducedRate.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error converting JsonNode to reducedRate", e);
        }
    }

    @Override
    public String toString() {
        return "CountryRate{" +
                "country='" + country + '\'' +
                ", standard_rate=" + standardRate +
                ", reduced_rate=" + reducedRate +
                ", reduced_rate_alt=" + reducedRateAlt +
                ", super_reduced_rate=" + superReducedRate +
                ", parking_rate=" + parkingRate +
                '}';
    }
}
