package com.sda.groupa.shippingcostcalculator.expeditionSummary.service;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.expeditionSummary.model.ExpeditionSummary;
import com.sda.groupa.shippingcostcalculator.expeditionSummary.repository.ExpeditionSummaryRepository;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelService.FuelService;
import com.sda.groupa.shippingcostcalculator.truckParts.service.TruckPartsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ExpeditionSummaryService {

    private final ExpeditionSummaryRepository expeditionSummaryRepository;
    private final ExpeditionService expeditionService;
    private final FuelService fuelService;
    private final ExtraCostService extraCostService;
    private final TruckPartsService truckPartsService;


    public ExpeditionSummaryService(ExpeditionSummaryRepository expeditionSummaryRepository, ExpeditionService expeditionService, FuelService fuelService, ExtraCostService extraCostService, TruckPartsService truckPartsService) {
        this.expeditionSummaryRepository = expeditionSummaryRepository;
        this.expeditionService = expeditionService;
        this.fuelService = fuelService;
        this.extraCostService = extraCostService;
        this.truckPartsService = truckPartsService;
    }

    public ExpeditionSummary findByExpeditionId(Long id) {
        return expeditionSummaryRepository.findByExpeditionId(id);
    }

    public BigDecimal countTotalCostOfFuelingsExtraCostsAndTruckParts(Expedition expedition) {
        return fuelService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition)
                .add(extraCostService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition)
                        .add(truckPartsService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition)));
    }
    public ExpeditionSummary getSummaryOfExpedition(Expedition expedition) {
        ExpeditionSummary expeditionSummary = ExpeditionSummary.builder()
                .expedition(expedition)
                .durationOfExpedition(expeditionService.countDurationOfExpedition(expedition))
                .totalCostsOfFuelingsExtraCostsAndTruckParts(countTotalCostOfFuelingsExtraCostsAndTruckParts(expedition))
                .sumOfFuelingsPayedInCurrencyOfPLN(fuelService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition))
                .sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN(fuelService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition))
                .totalCostOfAllFuelings(fuelService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition))
                .sumOfLitersInPoland(fuelService.calculateSumOfLitersInPoland(expedition))
                .mediumPriceForOneLiterInPoland(fuelService.calculateMediumPriceForOneLiterInPoland(expedition))
                .sumOfLitersAbroad(fuelService.calculateSumOfLitersAbroad(expedition))
                .mediumPriceForOneLiterInAbroad(fuelService.calculateMediumPriceForOneLiterInAbroad(expedition))
                .sumOfLiters(fuelService.calculateSumOfLiters(expedition))
                .mediumPriceForOneLiter(fuelService.calculateMediumPriceForOneLiter(expedition))
                .sumOfExtraCostsPayedInCurrencyOfPLN(extraCostService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition))
                .sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN(extraCostService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition))
                .totalCostOfAllExtraCosts(extraCostService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition))
                .sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN(truckPartsService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition))
                .sumOfTruckPartCostsPayedInCurrencyOfPLN(truckPartsService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition))
                .totalCostOfAllTruckParts(truckPartsService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition))
                .kilometersTravelled(expeditionService.countKilometersTravelled(expedition))
                .build();
        return expeditionSummary;
    }

}
