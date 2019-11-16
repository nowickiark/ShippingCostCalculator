package com.sda.groupa.shippingcostcalculator.truckParts.service;

import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import com.sda.groupa.shippingcostcalculator.truckParts.repository.TruckPartsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TruckPartsService implements CostCalculator {
    private final TruckPartsRepository truckPartsRepository;
    private CurrencyRateService currencyRateService;

    public TruckPartsService(TruckPartsRepository truckPartsRepository, CurrencyRateService currencyRateService) {
        this.truckPartsRepository = truckPartsRepository;
        this.currencyRateService = currencyRateService;
    }

    public void addTruckParts(TruckParts truckParts) {
        truckPartsRepository.save(truckParts);
    }

    public Optional<TruckParts> getById(Long id) {
        return truckPartsRepository.findById(id) ;
    }

    public List<TruckParts> getTruckParts(){
        return truckPartsRepository.findAll();
    }

    public List<TruckParts> getTruckPartsByExpedition(Expedition expedition){
        return truckPartsRepository.findTruckPartsByExpedition(expedition);
    }
    //======================CALCULATIONS FOR COSTS OF TRUCK PARTS================================
    //==== calculates sum of costs of truck parts payed in a choosen currency other than PLN======
    private BigDecimal calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(CurrencyCode currencyCode, Expedition expedition) {
        List<TruckParts> listOfTruckPartsCostsWithGivenCurrencyCode = truckPartsRepository.findTruckPartsByExpeditionAndAndCurrencyCode(expedition, currencyCode)
                .stream().filter(extraCost -> !extraCost.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());;
        BigDecimal sumOfTruckPartsCosts = new BigDecimal(0.0);
        for(TruckParts truckPart : listOfTruckPartsCostsWithGivenCurrencyCode){
            BigDecimal costOfSingleTruckPartCost = truckPart.getCost();
            LocalDate dateOfPayment = truckPart.getDateOfPurchase();
            sumOfTruckPartsCosts = sumOfTruckPartsCosts.add(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleTruckPartCost, currencyCode, dateOfPayment));
        }
        return sumOfTruckPartsCosts;
    }

    //=============sum of costs JUST for truck parts payed in PLN=========
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN(Expedition expedition) {
        List<TruckParts> listOfTruckPartCostsPayedInPLN = truckPartsRepository.findTruckPartsByExpeditionAndAndCurrencyCode(expedition, CurrencyCode.PLN);
        BigDecimal sumOfTruckPartCosts=new BigDecimal(0.0);
        for (TruckParts truckPart : listOfTruckPartCostsPayedInPLN) {
            BigDecimal costOfSingleTruckPartCost = truckPart.getCost();
            sumOfTruckPartCosts = sumOfTruckPartCosts.add(costOfSingleTruckPartCost);
        }
        return sumOfTruckPartCosts;
    }
    //====calculates all costs payed in choosen currency (given in parameter)====
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOf(CurrencyCode currencyCode, Expedition expedition) {
        if(currencyCode.equals(CurrencyCode.PLN)) {
            return calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        }else {
            return calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(currencyCode, expedition);
        }
    }

    //=============sum of costs for truck parts payed in ALL foreign currencies together==============
    @Override
    public BigDecimal calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(Expedition expedition) {
        List<TruckParts> listOfTruckPartCostsFromGivenExpedition = truckPartsRepository.findTruckPartsByExpedition(expedition);
        List<TruckParts> listOfTruckPartCostsFromGivenExpeditionPayedInForeignCurrency = listOfTruckPartCostsFromGivenExpedition.stream()
                .filter(truckPartCost -> !truckPartCost.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());

        BigDecimal sumOfCosts = new BigDecimal(0.0);
        for (TruckParts truckPart : listOfTruckPartCostsFromGivenExpeditionPayedInForeignCurrency) {
            BigDecimal costOfSingleExtraCost = truckPart.getCost();
            CurrencyCode currencyCode = truckPart.getCurrencyCode();
            LocalDate dateOfPayment = truckPart.getDateOfPurchase();
            BigDecimal costInPLN = currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleExtraCost, currencyCode, dateOfPayment);
            sumOfCosts = sumOfCosts.add(costInPLN);
        }
        return sumOfCosts;
    }

    //====sum of costs of ALL truck parts payed in ALL currencies=====
    @Override
    public BigDecimal calculateTotalCostsPayedInPLNandOtherCurrencies(Expedition expedition){
        BigDecimal extraCostsPayedInForeignCurrencies = calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        BigDecimal extraCostsPayedInPLN = calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        return extraCostsPayedInForeignCurrencies.add(extraCostsPayedInPLN);
    }


}
