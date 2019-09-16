package com.sda.groupa.shippingcostcalculator.freightRate.model;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class FreightRate {

    @Id
    @GeneratedValue(generator = "freightSeq")
    @SequenceGenerator(name = "freightSeq", sequenceName = "freight_seq", allocationSize = 1)
    private long id;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
    private String freightCompany;
    private long freightDistance;
    @ManyToOne(targetEntity = Expedition.class)
    private Expedition expedition;
    private LocalDate date;

    public FreightRate(){};

    public FreightRate(BigDecimal amount, CurrencyCode currencyCode, String freightCompany, long freightDistance, Expedition expedition, LocalDate date) {
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.freightCompany = freightCompany;
        this.freightDistance = freightDistance;
        this.expedition = expedition;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFreightCompany() {
        return freightCompany;
    }

    public void setFreightCompany(String freightCompany) {
        this.freightCompany = freightCompany;
    }

    public long getFreightDistance() {
        return freightDistance;
    }

    public void setFreightDistance(long freightDistance) {
        this.freightDistance = freightDistance;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreightRate that = (FreightRate) o;
        return id == that.id &&
                freightDistance == that.freightDistance &&
                Objects.equals(amount, that.amount) &&
                currencyCode == that.currencyCode &&
                Objects.equals(freightCompany, that.freightCompany) &&
                Objects.equals(expedition, that.expedition) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currencyCode, freightCompany, freightDistance, expedition, date);
    }
}
