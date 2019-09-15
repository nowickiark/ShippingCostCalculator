package com.sda.groupa.shippingcostcalculator.fuel.fuelModel;



import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Fuel {

    @Id
    @GeneratedValue(generator = "fuelSeq")
    @SequenceGenerator(name = "fuelSeq", sequenceName = "fuel_seq", allocationSize = 1)
    private Long id;
    private BigDecimal liters;
    private String placeOfRefueling;
    private BigDecimal cost;
    private CurrencyCode currencyCode;
    private Long kilometers;
    private String paymentMethod;
    private boolean refuelingToFull;
    @ManyToOne(targetEntity = Expedition.class)
    private Expedition expedition;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFueling;

    public Fuel(){
    };

    public Fuel(BigDecimal liters, String placeOfRefueling, BigDecimal cost, CurrencyCode currencyCode, Long kilometers, String paymentMethod, boolean refuelingToFull, Expedition expedition, LocalDate dateOfFueling) {
        this.liters = liters;
        this.placeOfRefueling = placeOfRefueling;
        this.cost = cost;
        this.currencyCode = currencyCode;
        this.kilometers = kilometers;
        this.paymentMethod = paymentMethod;
        this.refuelingToFull = refuelingToFull;
        this.expedition = expedition;
        this.dateOfFueling = dateOfFueling;
    }

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

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
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

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public LocalDate getDateOfFueling() {
        return dateOfFueling;
    }

    public void setDateOfFueling(LocalDate dateOfFueling) {
        this.dateOfFueling = dateOfFueling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return refuelingToFull == fuel.refuelingToFull &&
                Objects.equals(id, fuel.id) &&
                Objects.equals(liters, fuel.liters) &&
                Objects.equals(placeOfRefueling, fuel.placeOfRefueling) &&
                Objects.equals(cost, fuel.cost) &&
                Objects.equals(currencyCode, fuel.currencyCode) &&
                Objects.equals(kilometers, fuel.kilometers) &&
                Objects.equals(paymentMethod, fuel.paymentMethod) &&
                Objects.equals(expedition, fuel.expedition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, liters, placeOfRefueling, cost, currencyCode, kilometers, paymentMethod, refuelingToFull, expedition);
    }
}
