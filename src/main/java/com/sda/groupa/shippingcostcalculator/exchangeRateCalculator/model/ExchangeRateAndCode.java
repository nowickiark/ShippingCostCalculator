package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ExchangeRateAndCode {

    @Id
    @GeneratedValue(generator = "exchangeRateSeq")
    @SequenceGenerator(name = "exchangeRateSeq", sequenceName = "exchange_rate_seq", allocationSize = 1)
    private Long id;
    private LocalDate dateOfExchangeRate;
    private CurrencyCode currencyCode;
    private BigDecimal currencyRate;

    public ExchangeRateAndCode(LocalDate dateOfExchangeRate, CurrencyCode currencyCode, BigDecimal currencyRate) {
        this.dateOfExchangeRate = dateOfExchangeRate;
        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
    }

    public ExchangeRateAndCode() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfExchangeRate() {
        return dateOfExchangeRate;
    }

    public void setDateOfExchangeRate(LocalDate dateOfExchangeRate) {
        this.dateOfExchangeRate = dateOfExchangeRate;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRateAndCode that = (ExchangeRateAndCode) o;
        return id.equals(that.id) &&
                Objects.equals(dateOfExchangeRate, that.dateOfExchangeRate) &&
                currencyCode == that.currencyCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfExchangeRate, currencyCode);
    }
}
