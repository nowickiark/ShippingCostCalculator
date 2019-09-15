package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.dailyRate;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "table",
        "exchangeAndCodeRate",
        "code",
        "rates"
})


public class DailyRate {

    @JsonProperty("table")
    private String table;
    @JsonProperty("exchangeAndCodeRate")
    private String currency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rates")
    private List<Rate> rates = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("table")
    public String getTable() {
        return table;
    }

    @JsonProperty("table")
    public void setTable(String table) {
        this.table = table;
    }

    @JsonProperty("exchangeAndCodeRate")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("exchangeAndCodeRate")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("rates")
    public List<Rate> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
