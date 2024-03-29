package com.sda.groupa.shippingcostcalculator.expedition.model;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import org.springframework.format.annotation.DateTimeFormat;

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
    @ManyToOne(targetEntity = Driver.class)
    private Driver driver;
    private String startingPlace;
    private Long startOdometerReading;
    private Long endOdometerReading;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDay;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDay;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate closingDate;
    private BigDecimal cashBeginingZl;
    private BigDecimal cashEndZl;
    private BigDecimal cashBeginingEur;
    private BigDecimal cashEndEur;

    public Expedition(){};

    public Expedition(Truck truck,Driver driver, String startingPlace, long startOdometerReading, LocalDate startDay){
        this.truck = truck;
        this.driver = driver;
        this.startingPlace = startingPlace;
        this.startOdometerReading = startOdometerReading;
        this.startDay = startDay;
    }

    public Expedition(Truck truck, Driver driver, String startingPlace, Long startOdometerReading, Long endOdometerReading, LocalDate startDay, LocalDate endDay,LocalDate closingDate, BigDecimal cashBeginingZl, BigDecimal cashEndZl, BigDecimal cashBeginingEur, BigDecimal cashEndEur) {
        this.truck = truck;
        this.driver = driver;
        this.startingPlace = startingPlace;
        this.startOdometerReading = startOdometerReading;
        this.endOdometerReading = endOdometerReading;
        this.startDay = startDay;
        this.endDay = endDay;
        this.cashBeginingZl = cashBeginingZl;
        this.cashEndZl = cashEndZl;
        this.cashBeginingEur = cashBeginingEur;
        this.cashEndEur = cashEndEur;
        this.closingDate = closingDate;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
        return cashBeginingZl;
    }

    public void setCashBeginingZl(BigDecimal cashBeginingZl) {
        this.cashBeginingZl = cashBeginingZl;
    }

    public BigDecimal getCashEndZl() {
        return cashEndZl;
    }

    public void setCashEndZl(BigDecimal cashEndZl) {
        this.cashEndZl = cashEndZl;
    }

    public BigDecimal getCashBeginingEur() {
        return cashBeginingEur;
    }

    public void setCashBeginingEur(BigDecimal cashBeginingEur) {
        this.cashBeginingEur = cashBeginingEur;
    }

    public BigDecimal getCashEndEur() {
        return cashEndEur;
    }

    public void setCashEndEur(BigDecimal cashEndEur) {
        this.cashEndEur = cashEndEur;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expedition that = (Expedition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(truck, that.truck) &&
                Objects.equals(driver, that.driver) &&
                Objects.equals(startingPlace, that.startingPlace) &&
                Objects.equals(startOdometerReading, that.startOdometerReading) &&
                Objects.equals(endOdometerReading, that.endOdometerReading) &&
                Objects.equals(startDay, that.startDay) &&
                Objects.equals(endDay, that.endDay) &&
                Objects.equals(cashBeginingZl, that.cashBeginingZl) &&
                Objects.equals(cashEndZl, that.cashEndZl) &&
                Objects.equals(cashBeginingEur, that.cashBeginingEur) &&
                Objects.equals(cashEndEur, that.cashEndEur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, truck, driver, startingPlace, startOdometerReading, endOdometerReading, startDay, endDay, cashBeginingZl, cashEndZl, cashBeginingEur, cashEndEur);
    }
}
