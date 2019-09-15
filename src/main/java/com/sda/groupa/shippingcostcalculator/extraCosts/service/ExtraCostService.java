package com.sda.groupa.shippingcostcalculator.extraCosts.service;

import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.exception.NoLatestCurrencyReachedException;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.repository.ExtraCostRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtraCostService implements CostCalculator {

    private ExtraCostRepository extraCostRepository;
    private CurrencyRateService currencyRateService;

    public ExtraCostService(ExtraCostRepository extraCostRepository) {
        this.extraCostRepository = extraCostRepository;
    }

    public void addExtraCost(ExtraCost extraCost) {
        extraCostRepository.save(extraCost);
    }

    public Optional<ExtraCost> getById(Long id) {
        return extraCostRepository.findById(id) ;
    }

    public List<ExtraCost> getExtraCosts(){
        List<ExtraCost> extraCosts = extraCostRepository.findAll();

        return extraCosts;
    }

    public List<ExtraCost> getExtraCostsByExpetionId(Expedition expedition){
        return extraCostRepository.findExtraCostByExpedition(expedition);
    }

    //=============sum of costs JUST for extra costs payed in PLN=========
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN(Expedition expedition) {
        List<ExtraCost> listOfExtraCostsPayedInPLN = extraCostRepository.findExtraCostByExpeditionAndCurrencyCode(expedition, CurrencyCode.PLN);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (int i = 0; i < listOfExtraCostsPayedInPLN.size(); i++) {
            BigDecimal costOfSingleExtraCost = listOfExtraCostsPayedInPLN.get(i).getCost();
            sumOfCosts = sumOfCosts.add(costOfSingleExtraCost);
        }
        return sumOfCosts;
    }

    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOf(CurrencyCode currencyCode, Expedition expedition) {
        return null;
    }

    @Override
    public BigDecimal calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(Expedition expedition) {
        List<ExtraCost> listOfExtraCostsFromGivenExpedition = extraCostRepository.findExtraCostByExpedition(expedition);
        List<ExtraCost> listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency = listOfExtraCostsFromGivenExpedition.stream()
                .filter(extraCost -> !extraCost.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());

        BigDecimal latestCurrencyExchangeRate;
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (int i =0; i<listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency.size(); i++){
            BigDecimal costOfSingleExtraCost = listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency.get(i).getCost();
            try {
                latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(listOfExtraCostsFromGivenExpeditionPayedInForeignCurrency.get(i));
                sumOfCosts = sumOfCosts.add(costOfSingleExtraCost.multiply(latestCurrencyExchangeRate));
            } catch (IOException e) {
                throw  new NoLatestCurrencyReachedException();
            }
        }
        return sumOfCosts;
    }
}
