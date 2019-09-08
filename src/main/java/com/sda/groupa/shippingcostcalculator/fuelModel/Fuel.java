package com.sda.groupa.shippingcostcalculator.fuelModel;


import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Entity
public class Fuel {

    @Id
    @GeneratedValue(generator = "fuelSeq")
    @SequenceGenerator(name = "fuelSeq", sequenceName = "fuel_seq", allocationSize = 1)
    private Long id;
//    @ManyToOne
//    private Expedition expedition;
    private BigDecimal liters;
    private String placeOfRefueling;
    private BigDecimal cost;
    private Currency currency;
    private Long kilometers;
    private String paymentMethod;
    private boolean refuelingToFull;

    public Fuel(){

    };
//
//    public Fuel(Expedition expedition, BigDecimal liters, String placeOfRefueling, BigDecimal cost, Currency currency, Long kilometers, String paymentMethod, boolean refuelingToFull) {
//        this.expedition = expedition;
//        this.liters = liters;
//        this.placeOfRefueling = placeOfRefueling;
//        this.cost = cost;
//        this.currency = currency;
//        this.kilometers = kilometers;
//        this.paymentMethod = paymentMethod;
//        this.refuelingToFull = refuelingToFull;
//    }


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;}

    public BigDecimal getLiters() {
        return liters;
    }

    public void setLiters(BigDecimal liters) {
        this.liters = liters;
    }

    public String getPlaceOfRefueling() {
        return placeOfRefueling;
    }

    public void setPlaceOfRefueling(String placeOfRefueling) {
        this.placeOfRefueling = placeOfRefueling;
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

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isRefuelingToFull() {
        return refuelingToFull;
    }

    public void setRefuelingToFull(boolean refuelingToFull) {
        this.refuelingToFull = refuelingToFull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return getId().equals(fuel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}