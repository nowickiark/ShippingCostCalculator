package com.sda.groupa.shippingcostcalculator.journey.model;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Journey {

    @Id
    @GeneratedValue(generator = "journeySeq")
    @SequenceGenerator(name = "journeySeq", sequenceName = "journey_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(targetEntity = Expedition.class)
    private Expedition expedition;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String startPlace;
    private String endPlace;
    private Long meterReadingDeparture;
    private Long meterReadingArrival;
    private String comments;

    public Journey() {
    }

    public Journey(Expedition expedition, LocalDate startDate, LocalDate endDate, String startPlace, String endPlace, Long meterReadingDeparture, Long meterReadingArrival, String comments) {
        this.expedition = expedition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.meterReadingDeparture = meterReadingDeparture;
        this.meterReadingArrival = meterReadingArrival;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public Long getMeterReadingDeparture() {
        return meterReadingDeparture;
    }

    public void setMeterReadingDeparture(Long meterReadingDeparture) {
        this.meterReadingDeparture = meterReadingDeparture;
    }

    public Long getMeterReadingArrival() {
        return meterReadingArrival;
    }

    public void setMeterReadingArrival(Long meterReadingArrival) {
        this.meterReadingArrival = meterReadingArrival;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(id, journey.id) &&
                Objects.equals(startDate, journey.startDate) &&
                Objects.equals(endDate, journey.endDate) &&
                Objects.equals(startPlace, journey.startPlace) &&
                Objects.equals(endPlace, journey.endPlace) &&
                Objects.equals(meterReadingDeparture, journey.meterReadingDeparture) &&
                Objects.equals(meterReadingArrival, journey.meterReadingArrival) &&
                Objects.equals(comments, journey.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, startPlace, endPlace, meterReadingDeparture, meterReadingArrival, comments);
    }
}