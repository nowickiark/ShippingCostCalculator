package com.sda.groupa.shippingcostcalculator.shipperView.controller;


import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelService.FuelService;
import com.sda.groupa.shippingcostcalculator.truckParts.service.TruckPartsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class ShipperViewController {
    private final FuelService fuelService;
    private final ExtraCostService extraCostService;
    private final TruckPartsService truckPartsService;
    private final ExpeditionService expeditionService;

    public ShipperViewController(FuelService fuelService, ExtraCostService extraCostService, TruckPartsService truckPartsService, ExpeditionService expeditionService){
        this.fuelService = fuelService;
        this.extraCostService = extraCostService;
        this.truckPartsService = truckPartsService;
        this.expeditionService = expeditionService;
    }

    //=========SUMMARY OF EXPEDITION COSTS FOR THE VIEW OF SHIPPER ========
    @GetMapping(value="/expedition/fuelStatistics/{idExpedition}")
    public ModelAndView getSummaryOfExpeditionCostsView(@PathVariable Long idExpedition){
        Optional<Expedition> expeditionOptional = expeditionService.getExpeditionById(idExpedition);
        Expedition expedition = expeditionOptional.get(); //nie wiem czy mam tu dawac if z isPresent() czy po prostu to pominać
        ModelAndView modelAndView = new ModelAndView("summary");//TODO nazwę widoku może być zmieniona

        //======FUELINGS=======
        BigDecimal sumOfFuelingsPayedInCurrencyOfPLN = fuelService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        modelAndView.addObject("sumOfFuelingsPayedInCurrencyOfPLN", sumOfFuelingsPayedInCurrencyOfPLN);
        BigDecimal sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN = fuelService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        modelAndView.addObject("sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN", sumInPLNofFuelingsPayedInAllCurrenciesOtherThanPLN);
        BigDecimal totalCostOfAllFuelings = fuelService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition);
        modelAndView.addObject("totalCostOfAllFuelings", totalCostOfAllFuelings);

        BigDecimal sumOfLitersInPoland = fuelService.calculateSumOfLitersInPoland(expedition);
        modelAndView.addObject("sumOfLitersInPoland", sumOfLitersInPoland);
        BigDecimal mediumPriceForOneLiterInPoland = fuelService.calculateMediumPriceForOneLiterInPoland(expedition);
        modelAndView.addObject("mediumPriceForOneLiterInPoland", mediumPriceForOneLiterInPoland);

        BigDecimal sumOfLitersAbroad = fuelService.calculateSumOfLitersAbroad(expedition);
        modelAndView.addObject("sumOfLitersAbroad", sumOfLitersAbroad);
        BigDecimal mediumPriceForOneLiterInAbroad = fuelService.calculateMediumPriceForOneLiterInAbroad(expedition);
        modelAndView.addObject("mediumPriceForOneLiterInAbroad", mediumPriceForOneLiterInAbroad);

        BigDecimal sumOfLiters = fuelService.calculateSumOfLiters(expedition);
        modelAndView.addObject("sumOfLiters", sumOfLiters);
        BigDecimal mediumPriceForOneLiter = fuelService.calculateMediumPriceForOneLiter(expedition);
        modelAndView.addObject("mediumPriceForOneLiter", mediumPriceForOneLiter);

        //======EXTRA COST=====
        BigDecimal sumOfExtraCostsPayedInCurrencyOfPLN = extraCostService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        modelAndView.addObject("sumOfExtraCostsPayedInCurrencyOfPLN", sumOfExtraCostsPayedInCurrencyOfPLN);

        BigDecimal sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN = extraCostService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        modelAndView.addObject("sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN", sumInPLNofExtraCostsPayedInAllCurrenciesOtherThanPLN);

        BigDecimal totalCostOfAllExtraCosts = extraCostService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition);
        modelAndView.addObject("totalCostOfAllExtraCosts", totalCostOfAllExtraCosts);

        //======TRUCK PARTS====
        BigDecimal sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN = truckPartsService.calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        modelAndView.addObject("sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN", sumInPLNofTruckPartCostsPayedInAllCurrenciesOtherThanPLN);

        BigDecimal sumOfTruckPartCostsPayedInCurrencyOfPLN = truckPartsService.calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        modelAndView.addObject("sumOfCostsPayedInCurrencyOfPLN", sumOfTruckPartCostsPayedInCurrencyOfPLN);

        BigDecimal totalCostOfAllTruckParts = truckPartsService.calculateTotalCostsPayedInPLNandOtherCurrencies(expedition);
        modelAndView.addObject("", totalCostOfAllTruckParts);

        return modelAndView;
    }
}
