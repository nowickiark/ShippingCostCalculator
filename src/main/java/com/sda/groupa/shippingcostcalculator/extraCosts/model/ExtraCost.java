package com.sda.groupa.shippingcostcalculator.extraCosts.model;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class ExtraCost {

    @Id
    @GeneratedValue(generator = "eCostSeq")
    @SequenceGenerator(name = "eCostSeq", sequenceName = "e_cost_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(targetEntity = Expedition.class)
    private Expedition expedition;
    private String description;
    private BigDecimal cost;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPurchase;


    public ExtraCost() {
    }

    public ExtraCost(String description, BigDecimal cost, CurrencyCode currencyCode, LocalDate dateOfPurchase, Expedition expedition) {
        this.description = description;
        this.cost = cost;
        this.currencyCode = currencyCode;
        this.dateOfPurchase = dateOfPurchase;
        this.expedition = expedition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraCost extraCost = (ExtraCost) o;
        return Objects.equals(id, extraCost.id) &&
                Objects.equals(description, extraCost.description) &&
                Objects.equals(cost, extraCost.cost) &&
                currencyCode == extraCost.currencyCode &&
                Objects.equals(dateOfPurchase, extraCost.dateOfPurchase) &&
                Objects.equals(expedition, extraCost.expedition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, cost, currencyCode, dateOfPurchase, expedition);
    }
}
