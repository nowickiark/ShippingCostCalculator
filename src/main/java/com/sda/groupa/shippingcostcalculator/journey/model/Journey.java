package com.sda.groupa.shippingcostcalculator.journey.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Journey {

    @Id
    @GeneratedValue(generator = "journeySeq")
    @SequenceGenerator(name = "journeySeq", sequenceName = "journey_seq", allocationSize = 1)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    private String route;
    private String routeDestination;
    private String meterReadingDeparture;
    private String meterReadingArrival;
    private String mileage;
    private String comments;

    public Journey() {
    }

    public Journey(LocalDateTime startDate, LocalDateTime endDate, String route, String routeDestination, String meterReadingDeparture, String meterReadingArrival, String mileage, String comments) {
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

    public String getMeterReadingDeparture() {
        return meterReadingDeparture;
    }

    public void setMeterReadingDeparture(String meterReadingDeparture) {
        this.meterReadingDeparture = meterReadingDeparture;
    }

    public String getMeterReadingArrival() {
        return meterReadingArrival;
    }

    public void setMeterReadingArrival(String meterReadingArrival) {
        this.meterReadingArrival = meterReadingArrival;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
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