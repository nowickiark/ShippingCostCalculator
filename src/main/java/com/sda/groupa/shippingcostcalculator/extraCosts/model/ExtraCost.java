package com.sda.groupa.shippingcostcalculator.extraCosts.model;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

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
    private Currency currency;
    private LocalDate dateOfPurchase;


    public ExtraCost() {
    }

    public ExtraCost(String description, BigDecimal cost, Currency currency, LocalDate dateOfPurchase, Expedition expedition) {
        this.description = description;
        this.cost = cost;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
                currency == extraCost.currency &&
                Objects.equals(dateOfPurchase, extraCost.dateOfPurchase) &&
                Objects.equals(expedition, extraCost.expedition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, cost, currency, dateOfPurchase, expedition);
    }
}
