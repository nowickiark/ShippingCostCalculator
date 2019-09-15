package com.sda.groupa.shippingcostcalculator.journey.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Journey {

    @Id
    @GeneratedValue(generator = "journeySeq")
    @SequenceGenerator(name = "journeySeq", sequenceName = "journey_seq", allocationSize = 1)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    private String route;
    private String routeDestination;
    private String meterReadingDeparture;
    private String meterReadingArrival;
    private String mileage;
    private String comments;

    public Journey() {
    }

    public Journey(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String route, String routeDestination, String meterReadingDeparture, String meterReadingArrival, String mileage, String comments) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
                Objects.equals(startTime, journey.startTime) &&
                Objects.equals(endTime, journey.endTime) &&
                Objects.equals(route, journey.route) &&
                Objects.equals(routeDestination, journey.routeDestination) &&
                Objects.equals(meterReadingDeparture, journey.meterReadingDeparture) &&
                Objects.equals(meterReadingArrival, journey.meterReadingArrival) &&
                Objects.equals(mileage, journey.mileage) &&
                Objects.equals(comments, journey.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, startTime, endTime, route, routeDestination, meterReadingDeparture, meterReadingArrival, mileage, comments);
    }
}