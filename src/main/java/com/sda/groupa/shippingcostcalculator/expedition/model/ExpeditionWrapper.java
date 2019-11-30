package com.sda.groupa.shippingcostcalculator.expedition.model;

import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.print.attribute.standard.JobHoldUntil;
import java.time.LocalDate;

@Component
public class ExpeditionWrapper {

    private Expedition expedition;
    private FreightRate freightRate;
    private Journey journey;
    private double averageMilage;
    private boolean onRoad;

    public ExpeditionWrapper(){}

    @Builder
    public ExpeditionWrapper(Expedition expedition, FreightRate freightRate, Journey journey, double averageMilage, boolean onRoad) {
        this.expedition = expedition;
        this.freightRate = freightRate;
        this.journey = journey;
        this.averageMilage = averageMilage;
        this.onRoad = onRoad;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public FreightRate getFreightRate() {
        return freightRate;
    }

    public void setFreightRate(FreightRate freightRate) {
        this.freightRate = freightRate;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public double getAverageMilage() {
        return averageMilage;
    }

    public void setAverageMilage(double averageMilage) {
        this.averageMilage = averageMilage;
    }

    public boolean isOnRoad() {
        return onRoad;
    }

    public void setOnRoad(boolean onRoad) {
        this.onRoad = onRoad;
    }

    @Override
    public String toString() {
        return "ExpeditionWrapper{" +
                "expedition=" + expedition +
                ", freightRate=" + freightRate +
                ", journey=" + journey +
                ", averageMilage=" + averageMilage +
                ", onRoad=" + onRoad +
                '}';
    }
}
