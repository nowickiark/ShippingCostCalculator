package com.sda.groupa.shippingcostcalculator.extraCosts.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class ExtraCost {

    @Id
    @GeneratedValue(generator = "eCostSeq")
    @SequenceGenerator(name = "eCostSeq", sequenceName = "e_cost_seq", allocationSize = 1)
    private Long id;
    private String description;
    private Double cost;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDate dateOfPurchase;

    public ExtraCost() {
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
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

    public ExtraCost(String description, Double cost, Currency currency, LocalDate dateOfPurchase) {
        this.description = description;
        this.cost = cost;
        this.currency = currency;
        this.dateOfPurchase = dateOfPurchase;
    }
}
