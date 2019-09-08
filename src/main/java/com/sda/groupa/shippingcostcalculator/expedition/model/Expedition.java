package com.sda.groupa.shippingcostcalculator.expedition.model;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Expedition {

    @Id
    @GeneratedValue(generator = "expeditionSeq")
    @SequenceGenerator(name = "expeditionSeq", sequenceName = "expedition_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Truck.class)
    private Truck truck;

    private String startingPlace;
    private Long startOdometerReading;
    private Long endOdometerReading;

    private LocalDate startDay;
    private LocalDate endDay;

    private BigDecimal CashBeginingZl;
    private BigDecimal CashEndZl;
    private BigDecimal CashBeginingEur;
    private BigDecimal CashEndEur;

    public Expedition(){};

    public Expedition(Truck truck,String startingPlace, long startOdometerReading, LocalDate startDay){
        this.truck = truck;
        this.startingPlace = startingPlace;
        this.startOdometerReading = startOdometerReading;
        this.startDay = startDay;
    }

    public Expedition(Truck truck, String startingPlace, long startOdometerReading, long endOdometerReading, LocalDate startDay, LocalDate endDay, BigDecimal cashBeginingZl, BigDecimal cashEndZl, BigDecimal cashBeginingEur, BigDecimal cashEndEur) {
        this.truck = truck;
        this.startingPlace = startingPlace;
        this.startOdometerReading = startOdometerReading;
        this.endOdometerReading = endOdometerReading;
        this.startDay = startDay;
        this.endDay = endDay;
        CashBeginingZl = cashBeginingZl;
        CashEndZl = cashEndZl;
        CashBeginingEur = cashBeginingEur;
        CashEndEur = cashEndEur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getStartingPlace() {
        return startingPlace;
    }

    public void setStartingPlace(String startingPlace) {
        this.startingPlace = startingPlace;
    }

    public Long getStartOdometerReading() {
        return startOdometerReading;
    }

    public void setStartOdometerReading(Long startOdometerReading) {
        this.startOdometerReading = startOdometerReading;
    }

    public Long getEndOdometerReading() {
        return endOdometerReading;
    }

    public void setEndOdometerReading(Long endOdometerReading) {
        this.endOdometerReading = endOdometerReading;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public BigDecimal getCashBeginingZl() {
        return CashBeginingZl;
    }

    public void setCashBeginingZl(BigDecimal cashBeginingZl) {
        CashBeginingZl = cashBeginingZl;
    }

    public BigDecimal getCashEndZl() {
        return CashEndZl;
    }

    public void setCashEndZl(BigDecimal cashEndZl) {
        CashEndZl = cashEndZl;
    }

    public BigDecimal getCashBeginingEur() {
        return CashBeginingEur;
    }

    public void setCashBeginingEur(BigDecimal cashBeginingEur) {
        CashBeginingEur = cashBeginingEur;
    }

    public BigDecimal getCashEndEur() {
        return CashEndEur;
    }

    public void setCashEndEur(BigDecimal cashEndEur) {
        CashEndEur = cashEndEur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expedition that = (Expedition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(truck, that.truck) &&
                Objects.equals(startingPlace, that.startingPlace) &&
                Objects.equals(startOdometerReading, that.startOdometerReading) &&
                Objects.equals(endOdometerReading, that.endOdometerReading) &&
                Objects.equals(startDay, that.startDay) &&
                Objects.equals(endDay, that.endDay) &&
                Objects.equals(CashBeginingZl, that.CashBeginingZl) &&
                Objects.equals(CashEndZl, that.CashEndZl) &&
                Objects.equals(CashBeginingEur, that.CashBeginingEur) &&
                Objects.equals(CashEndEur, that.CashEndEur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, truck, startingPlace, startOdometerReading, endOdometerReading, startDay, endDay, CashBeginingZl, CashEndZl, CashBeginingEur, CashEndEur);
    }
}
