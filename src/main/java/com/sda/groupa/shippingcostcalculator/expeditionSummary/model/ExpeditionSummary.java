package com.sda.groupa.shippingcostcalculator.expeditionSummary.model;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class ExpeditionSummary {

    @Id
    @GeneratedValue(generator = "summarySeq")
    @SequenceGenerator(name = "summarySeq", sequenceName = "summary_seq", allocationSize = 1)
    private Long id;
    @OneToOne (targetEntity = Expedition.class)//TODO czy dobrze?
    private Expedition expedition;
    private Long durationOfExpedition;
    private BigDecimal totalCostsOfFuelingsExtraCostsAndTruckParts;
    //======FUELINGS================
    private BigDecimal sumOfFuelingsPayedInCurrencyOfPLN;
    private BigDecimal sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN;
    private BigDecimal totalCostOfAllFuelings;
    private BigDecimal sumOfLitersInPoland;
    private BigDecimal mediumPriceForOneLiterInPoland;
    private BigDecimal sumOfLitersAbroad;
    private BigDecimal mediumPriceForOneLiterInAbroad;
    private BigDecimal sumOfLiters;
    private BigDecimal mediumPriceForOneLiter;
    //======EXTRA COST==============
    private BigDecimal sumOfExtraCostsPayedInCurrencyOfPLN;
    private BigDecimal sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN;
    private BigDecimal totalCostOfAllExtraCosts;
    //======TRUCK PARTS=============
    private BigDecimal sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN;
    private BigDecimal sumOfTruckPartCostsPayedInCurrencyOfPLN;
    private BigDecimal totalCostOfAllTruckParts;
    //======FRIGHT RATES============

    //======KKILOMETERS TRAVELLED===
    private Long kilometersTravelled;

    public ExpeditionSummary() {}

    @Builder
    public ExpeditionSummary(Expedition expedition, Long durationOfExpedition, BigDecimal totalCostsOfFuelingsExtraCostsAndTruckParts, BigDecimal sumOfFuelingsPayedInCurrencyOfPLN, BigDecimal sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN, BigDecimal totalCostOfAllFuelings, BigDecimal sumOfLitersInPoland, BigDecimal mediumPriceForOneLiterInPoland, BigDecimal sumOfLitersAbroad, BigDecimal mediumPriceForOneLiterInAbroad, BigDecimal sumOfLiters, BigDecimal mediumPriceForOneLiter, BigDecimal sumOfExtraCostsPayedInCurrencyOfPLN, BigDecimal sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN, BigDecimal totalCostOfAllExtraCosts, BigDecimal sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN, BigDecimal sumOfTruckPartCostsPayedInCurrencyOfPLN, BigDecimal totalCostOfAllTruckParts, Long kilometersTravelled) {
        this.expedition = expedition;
        this.durationOfExpedition = durationOfExpedition;
        this.totalCostsOfFuelingsExtraCostsAndTruckParts = totalCostsOfFuelingsExtraCostsAndTruckParts;
        this.sumOfFuelingsPayedInCurrencyOfPLN = sumOfFuelingsPayedInCurrencyOfPLN;
        this.sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN = sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN;
        this.totalCostOfAllFuelings = totalCostOfAllFuelings;
        this.sumOfLitersInPoland = sumOfLitersInPoland;
        this.mediumPriceForOneLiterInPoland = mediumPriceForOneLiterInPoland;
        this.sumOfLitersAbroad = sumOfLitersAbroad;
        this.mediumPriceForOneLiterInAbroad = mediumPriceForOneLiterInAbroad;
        this.sumOfLiters = sumOfLiters;
        this.mediumPriceForOneLiter = mediumPriceForOneLiter;
        this.sumOfExtraCostsPayedInCurrencyOfPLN = sumOfExtraCostsPayedInCurrencyOfPLN;
        this.sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN = sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN;
        this.totalCostOfAllExtraCosts = totalCostOfAllExtraCosts;
        this.sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN = sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN;
        this.sumOfTruckPartCostsPayedInCurrencyOfPLN = sumOfTruckPartCostsPayedInCurrencyOfPLN;
        this.totalCostOfAllTruckParts = totalCostOfAllTruckParts;
        this.kilometersTravelled = kilometersTravelled;
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

    public Long getDurationOfExpedition() {
        return durationOfExpedition;
    }

    public void setDurationOfExpedition(Long durationOfExpedition) {
        this.durationOfExpedition = durationOfExpedition;
    }

    public BigDecimal getTotalCostsOfFuelingsExtraCostsAndTruckParts() {
        return totalCostsOfFuelingsExtraCostsAndTruckParts;
    }

    public void setTotalCostsOfFuelingsExtraCostsAndTruckParts(BigDecimal totalCostsOfFuelingsExtraCostsAndTruckParts) {
        this.totalCostsOfFuelingsExtraCostsAndTruckParts = totalCostsOfFuelingsExtraCostsAndTruckParts;
    }

    public BigDecimal getSumOfFuelingsPayedInCurrencyOfPLN() {
        return sumOfFuelingsPayedInCurrencyOfPLN;
    }

    public void setSumOfFuelingsPayedInCurrencyOfPLN(BigDecimal sumOfFuelingsPayedInCurrencyOfPLN) {
        this.sumOfFuelingsPayedInCurrencyOfPLN = sumOfFuelingsPayedInCurrencyOfPLN;
    }

    public BigDecimal getSumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN() {
        return sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN;
    }

    public void setSumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN(BigDecimal sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN) {
        this.sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN = sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN;
    }

    public BigDecimal getTotalCostOfAllFuelings() {
        return totalCostOfAllFuelings;
    }

    public void setTotalCostOfAllFuelings(BigDecimal totalCostOfAllFuelings) {
        this.totalCostOfAllFuelings = totalCostOfAllFuelings;
    }

    public BigDecimal getSumOfLitersInPoland() {
        return sumOfLitersInPoland;
    }

    public void setSumOfLitersInPoland(BigDecimal sumOfLitersInPoland) {
        this.sumOfLitersInPoland = sumOfLitersInPoland;
    }

    public BigDecimal getMediumPriceForOneLiterInPoland() {
        return mediumPriceForOneLiterInPoland;
    }

    public void setMediumPriceForOneLiterInPoland(BigDecimal mediumPriceForOneLiterInPoland) {
        this.mediumPriceForOneLiterInPoland = mediumPriceForOneLiterInPoland;
    }

    public BigDecimal getSumOfLitersAbroad() {
        return sumOfLitersAbroad;
    }

    public void setSumOfLitersAbroad(BigDecimal sumOfLitersAbroad) {
        this.sumOfLitersAbroad = sumOfLitersAbroad;
    }

    public BigDecimal getMediumPriceForOneLiterInAbroad() {
        return mediumPriceForOneLiterInAbroad;
    }

    public void setMediumPriceForOneLiterInAbroad(BigDecimal mediumPriceForOneLiterInAbroad) {
        this.mediumPriceForOneLiterInAbroad = mediumPriceForOneLiterInAbroad;
    }

    public BigDecimal getSumOfLiters() {
        return sumOfLiters;
    }

    public void setSumOfLiters(BigDecimal sumOfLiters) {
        this.sumOfLiters = sumOfLiters;
    }

    public BigDecimal getMediumPriceForOneLiter() {
        return mediumPriceForOneLiter;
    }

    public void setMediumPriceForOneLiter(BigDecimal mediumPriceForOneLiter) {
        this.mediumPriceForOneLiter = mediumPriceForOneLiter;
    }

    public BigDecimal getSumOfExtraCostsPayedInCurrencyOfPLN() {
        return sumOfExtraCostsPayedInCurrencyOfPLN;
    }

    public void setSumOfExtraCostsPayedInCurrencyOfPLN(BigDecimal sumOfExtraCostsPayedInCurrencyOfPLN) {
        this.sumOfExtraCostsPayedInCurrencyOfPLN = sumOfExtraCostsPayedInCurrencyOfPLN;
    }

    public BigDecimal getSumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN() {
        return sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN;
    }

    public void setSumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN(BigDecimal sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN) {
        this.sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN = sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN;
    }

    public BigDecimal getTotalCostOfAllExtraCosts() {
        return totalCostOfAllExtraCosts;
    }

    public void setTotalCostOfAllExtraCosts(BigDecimal totalCostOfAllExtraCosts) {
        this.totalCostOfAllExtraCosts = totalCostOfAllExtraCosts;
    }

    public BigDecimal getSumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN() {
        return sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN;
    }

    public void setSumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN(BigDecimal sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN) {
        this.sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN = sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN;
    }

    public BigDecimal getSumOfTruckPartCostsPayedInCurrencyOfPLN() {
        return sumOfTruckPartCostsPayedInCurrencyOfPLN;
    }

    public void setSumOfTruckPartCostsPayedInCurrencyOfPLN(BigDecimal sumOfTruckPartCostsPayedInCurrencyOfPLN) {
        this.sumOfTruckPartCostsPayedInCurrencyOfPLN = sumOfTruckPartCostsPayedInCurrencyOfPLN;
    }

    public BigDecimal getTotalCostOfAllTruckParts() {
        return totalCostOfAllTruckParts;
    }

    public void setTotalCostOfAllTruckParts(BigDecimal totalCostOfAllTruckParts) {
        this.totalCostOfAllTruckParts = totalCostOfAllTruckParts;
    }

    public Long getKilometersTravelled() {
        return kilometersTravelled;
    }

    public void setKilometersTravelled(Long kilometersTravelled) {
        this.kilometersTravelled = kilometersTravelled;
    }

    @Override
    public String toString() {
        return "EXPEDITION SUMMARY:" + "\n" +
                "Summary id ; " + id + "\n" +
                "Expedition id ; " + expedition.getId() + "\n"+
                "Date of departure ; " + expedition.getStartDay().toString() + "\n" +
                "Date of arrival ; " + expedition.getEndDay().toString() + "\n" +
                "Driver's id ; " + expedition.getDriver().getId() + "\n" +
                "Driver's last name and first name; " + expedition.getDriver().getSurname() + " " + expedition.getDriver().getFirstName() + "\n"+
                "Duration of expedition ; " + durationOfExpedition + "\n" +
                "Kilometers travelled ; " + kilometersTravelled + " km" + "\n" +
                "\n" +
                "COSTS: " + "\n" +
                "FUEL: "+ "\n" +
                "Total costs (fuelings, extra costs, truck parts ; " + totalCostsOfFuelingsExtraCostsAndTruckParts + "\n" +
                "Cost of fuelings payed in PLN ; " + sumOfFuelingsPayedInCurrencyOfPLN + " PLN" + "\n" +
                "Cost of fuelings payed in foreign currencies ; " + sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN + " PLN" + "\n" +
                "Total cost of all fuelings ; " + totalCostOfAllFuelings + " PLN" + "\n" +
                "Sum of liters in Poland ; " + sumOfLitersInPoland + "\n" +
                "Mdium price for one liter in Poland ; " + mediumPriceForOneLiterInPoland + " PLN" + "\n" +
                "Sum of liters abroad ; " + sumOfLitersAbroad + "\n" +
                "Medium price for one liter in abroad ; " + mediumPriceForOneLiterInAbroad + " PLN" + "\n" +
                "Total sum of liters ; " + sumOfLiters + "\n" +
                "Medium price for one liter ; " + mediumPriceForOneLiter + "\n" +
                "EXTRA COSTS: " + "\n" +
                "Sum of extra costs payed in PLN=" + sumOfExtraCostsPayedInCurrencyOfPLN + " PLN" + "\n" +
                "Sum of extra costs payed in foreign currencies ; " + sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN + " PLN" + "\n" +
                "Total cost of all extra costs ; " + totalCostOfAllExtraCosts + " PLN" + "\n" +
                "TRUCK PARTS AND REPERATIONS: " + "\n" +
                "Sum of truck parts' and reperaitions' costs payed in foreign currencies ; " + sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN + "PLN" + "\n" +
                "Sum of truck parts' and reperations'  payed in PLN ; " + sumOfTruckPartCostsPayedInCurrencyOfPLN + " PLN" + "\n" +
                "Total cost of all truck parts' and reparations ; " + totalCostOfAllTruckParts + " PLN" + "\n";
    }
}
