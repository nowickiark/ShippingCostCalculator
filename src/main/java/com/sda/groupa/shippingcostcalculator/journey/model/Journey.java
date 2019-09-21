package com.sda.groupa.shippingcostcalculator.journey.model;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    private String route;
    private String routeDestination;
    private Long meterReadingDeparture;
    private Long meterReadingArrival;
    private Long mileage;
    private String comments;

    public Journey() {
    }

    public Journey(Expedition expedition, LocalDateTime startDate, LocalDateTime endDate, String route, String routeDestination, Long meterReadingDeparture, Long meterReadingArrival, Long mileage, String comments) {
        this.expedition = expedition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.route = route;
        this.routeDestination = routeDestination;
        this.meterReadingDeparture = meterReadingDeparture;
        this.meterReadingArrival = meterReadingArrival;
        this.mileage = mileage;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(String routeDestination) {
        this.routeDestination = routeDestination;
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

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
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
                Objects.equals(route, journey.route) &&
                Objects.equals(routeDestination, journey.routeDestination) &&
                Objects.equals(meterReadingDeparture, journey.meterReadingDeparture) &&
                Objects.equals(meterReadingArrival, journey.meterReadingArrival) &&
                Objects.equals(mileage, journey.mileage) &&
                Objects.equals(comments, journey.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, route, routeDestination, meterReadingDeparture, meterReadingArrival, mileage, comments);
    }
}