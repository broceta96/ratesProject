package com.rates.ratesproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class CountryRate {
    private String country;
    @JsonProperty("standard_rate")
    private double standardRate;
    @JsonProperty("reduced_rate")
    private JsonNode reducedRate;
    @JsonProperty("reduced_rate_alt")
    private JsonNode reducedRateAlt;
    @JsonProperty("super_reduced_rate")
    private JsonNode superReducedRate;
    @JsonProperty("parking_rate")
    private JsonNode parkingRate;
    @JsonProperty("_comment")
    private String comment;
    @JsonProperty("iso_duplicate")
    private String isoDuplicate;
    @JsonProperty("iso_duplicate_of")
    private String isoDuplicateOf;

    public CountryRate(){}

    public CountryRate(String country, double standardRate, JsonNode reducedRate, JsonNode reducedRateAlt, JsonNode superReducedRate, JsonNode parkingRate) {
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

    public JsonNode getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(JsonNode reducedRate) {
        this.reducedRate = reducedRate;
    }

    public JsonNode getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(JsonNode reducedRateAlt) {
        this.reducedRateAlt = reducedRateAlt;
    }

    public JsonNode getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(JsonNode superReducedRate) {
        this.superReducedRate = superReducedRate;
    }

    public JsonNode getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(JsonNode parkingRate) {
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
