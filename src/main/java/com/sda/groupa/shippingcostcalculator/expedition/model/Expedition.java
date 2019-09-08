package com.sda.groupa.shippingcostcalculator.expedition.model;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Expedition {

    @Id
    @GeneratedValue(generator = "expeditionSeq")
    @SequenceGenerator(name = "expeditionSeq", sequenceName = "expedition_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Truck.class)
    private Truck truck;

    private String startingPlace;
    private long startOdometerReading;
    private long endOdometerReading;

    private LocalDate startDay;
    private LocalDate endDay;

    private double CashBeginingZl;
    private double CashEndZl;
    private double CashBeginingEur;
    private double CashEndEur;

    public Expedition(){};

    public Expedition(Truck truck, String startingPlace, long startOdometerReading, long endOdometerReading, LocalDate startDay, LocalDate endDay, double cashBeginingZl, double cashEndZl, double cashBeginingEur, double cashEndEur) {
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

    public long getStartOdometerReading() {
        return startOdometerReading;
    }

    public void setStartOdometerReading(long startOdometerReading) {
        this.startOdometerReading = startOdometerReading;
    }

    public long getEndOdometerReading() {
        return endOdometerReading;
    }

    public void setEndOdometerReading(long endOdometerReading) {
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

    public double getCashBeginingZl() {
        return CashBeginingZl;
    }

    public void setCashBeginingZl(double cashBeginingZl) {
        CashBeginingZl = cashBeginingZl;
    }

    public double getCashEndZl() {
        return CashEndZl;
    }

    public void setCashEndZl(double cashEndZl) {
        CashEndZl = cashEndZl;
    }

    public double getCashBeginingEur() {
        return CashBeginingEur;
    }

    public void setCashBeginingEur(double cashBeginingEur) {
        CashBeginingEur = cashBeginingEur;
    }

    public double getCashEndEur() {
        return CashEndEur;
    }

    public void setCashEndEur(double cashEndEur) {
        CashEndEur = cashEndEur;
    }

    @Override
    public String toString() {
        return "Expedition{" +
                "id=" + id +
                ", truck=" + truck +
                ", startingPlace='" + startingPlace + '\'' +
                ", startOdometerReading=" + startOdometerReading +
                ", endOdometerReading=" + endOdometerReading +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", CashBeginingZl=" + CashBeginingZl +
                ", CashEndZl=" + CashEndZl +
                ", CashBeginingEur=" + CashBeginingEur +
                ", CashEndEur=" + CashEndEur +
                '}';
    }
}
