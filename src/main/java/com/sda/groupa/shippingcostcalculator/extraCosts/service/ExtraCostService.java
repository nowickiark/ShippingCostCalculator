package com.sda.groupa.shippingcostcalculator.extraCosts.service;

import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.repository.ExtraCostRepository;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtraCostService implements CostCalculator {

    private ExtraCostRepository extraCostRepository;
    private CurrencyRateService currencyRateService;

    public ExtraCostService(ExtraCostRepository extraCostRepository, CurrencyRateService currencyRateService) {
        this.extraCostRepository = extraCostRepository;
        this.currencyRateService = currencyRateService;
    }

    public void addExtraCost(ExtraCost extraCost) {
        extraCostRepository.save(extraCost);
    }

    public Optional<ExtraCost> getById(Long id) {
        return extraCostRepository.findById(id) ;
    }

    public List<ExtraCost> getExtraCosts(){
        return extraCostRepository.findAll();
    }

    public List<ExtraCost> getExtraCostsByExpetion(Expedition expedition){
        return extraCostRepository.findExtraCostByExpedition(expedition);
    }
    //======================CALCULATIONS FOR COSTS OF EXTRA COSTS================================
    //==== calculates sum of costs of extra costs payed in a choosen currency other than PLN======
    private BigDecimal calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(CurrencyCode currencyCode, Expedition expedition) {
        List<ExtraCost> listOfExtraCostsWithGivenCurrencyCode = extraCostRepository
                .findExtraCostByExpeditionAndCurrencyCode(expedition, currencyCode)
                .stream().filter(extraCost -> !extraCost.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for(ExtraCost extraCost : listOfExtraCostsWithGivenCurrencyCode){
            BigDecimal costOfSingleExtraCost = extraCost.getCost();
            LocalDate dateOfPayment = extraCost.getDateOfPurchase();
            sumOfCosts = sumOfCosts.add(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleExtraCost,currencyCode,dateOfPayment));
        }
        return sumOfCosts;
    }

    //=============sum of costs JUST for extra costs payed in PLN=========
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN(Expedition expedition) {
        List<ExtraCost> listOfExtraCostsPayedInPLN = extraCostRepository.findExtraCostByExpeditionAndCurrencyCode(expedition, CurrencyCode.PLN);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (ExtraCost extraCost : listOfExtraCostsPayedInPLN) {
            BigDecimal costOfSingleExtraCost = extraCost.getCost();
            sumOfCosts = sumOfCosts.add(costOfSingleExtraCost);
        }
        return sumOfCosts;
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

    //=============sum of costs for extra costs payed in ALL foreign currencies together==============
    @Override
    public BigDecimal calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(Expedition expedition) {
        List<ExtraCost> listOfExtraCostsFromGivenExpedition = extraCostRepository.findExtraCostByExpedition(expedition);
        List<ExtraCost> listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency = listOfExtraCostsFromGivenExpedition.stream()
                .filter(extraCost -> !extraCost.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());

        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (ExtraCost extraCost : listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency){
            BigDecimal costOfSingleExtraCost = extraCost.getCost();
            CurrencyCode currencyCode = extraCost.getCurrencyCode();
            LocalDate dateOfPayment = extraCost.getDateOfPurchase();
            BigDecimal costInPLN = currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleExtraCost, currencyCode, dateOfPayment);
            sumOfCosts = sumOfCosts.add(costInPLN);
        }
        return sumOfCosts;
    }

    //====sum of costs of ALL extra costs payed in ALL currencies=====
    @Override
    public BigDecimal calculateTotalCostsPayedInPLNandOtherCurrencies(Expedition expedition){
        BigDecimal extraCostsPayedInForeignCurrencies = calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        BigDecimal extraCostsPayedInPLN = calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        return extraCostsPayedInForeignCurrencies.add(extraCostsPayedInPLN);
    }

}
