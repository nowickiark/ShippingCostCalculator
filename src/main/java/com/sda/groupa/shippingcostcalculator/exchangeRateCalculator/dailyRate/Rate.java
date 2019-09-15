package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.dailyRate;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "no",
        "effectiveDate",
        "bid",
        "ask"
})


public class Rate {
    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("bid")
    private Double bid; // bid <- calculated exchangeAndCodeRate buy exchange rate
    @JsonProperty("ask")
    private Double ask; // ask <- calculated exchangeAndCodeRate sell exchange rate
    @JsonProperty("mid")
    private Double mid; // bid <- calculated exchangeAndCodeRate buy exchange rate
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("no")
    public String getNo() {
        return no;
    }

    @JsonProperty("no")
    public void setNo(String no) {
        this.no = no;
    }

    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    @JsonProperty("effectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @JsonProperty("bid")
    public Double getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(Double bid) {
        this.bid = bid;
    }

    @JsonProperty("ask")
    public Double getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @JsonProperty("mid")
    public Double getMid() {
        return mid;
    }

    @JsonProperty("mid")
    public void setMid(Double mid) {
        this.mid = mid;
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
