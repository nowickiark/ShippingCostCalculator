package com.sda.groupa.shippingcostcalculator.fuel.fuelService;

import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.exception.NoLatestCurrencyReachedException;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuel.fuelRepository.FuelRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelService implements CostCalculator {

    private final FuelRepository fuelRepository;

    private CurrencyRateService currencyRateService;

    public FuelService (FuelRepository fuelRepository){
        this.fuelRepository = fuelRepository;
    }

    public void addFueling(Fuel fuel){
        fuelRepository.save(fuel);
    }

    public void removeFueling (Fuel fuel){
        fuelRepository.delete(fuel);
    }

    public List<Fuel> findAll(){
        return fuelRepository.findAll();
    }

    public void updateFueling(Fuel fuel){
        fuelRepository.save(fuel);
    }

    public Optional<Fuel> findById(Long id){
        return fuelRepository.findById(id);
    }

    public List<Fuel> findFuelsByExpedition(Expedition expedition){return fuelRepository.findFuelsByExpedition(expedition);}

    public List<Fuel> findFuelsByExpeditionAndAndCurrencyCode(Expedition expedition, CurrencyCode currencyCode){
        return fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);
    }

    //==== calculates sum of costs of fuelings payed in a choosen currency other than PLN======
    private BigDecimal calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(CurrencyCode currencyCode, Expedition expedition) {
        List<Fuel> listOfFuelingsWithGivenCurrencyCode = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);

        BigDecimal latestCurrencyExchangeRate;
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for(int i=0; i<listOfFuelingsWithGivenCurrencyCode.size(); i++){
            BigDecimal costOfSingleFueling = listOfFuelingsWithGivenCurrencyCode.get(i).getCost();
            try {
                latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(listOfFuelingsWithGivenCurrencyCode.get(i));
            } catch (IOException e) {
                throw new NoLatestCurrencyReachedException();   //nie wyświeltlać stack traca!!!
            }
            sumOfCosts = sumOfCosts.add(costOfSingleFueling.multiply(latestCurrencyExchangeRate));
        }
        return sumOfCosts;
    }

    //=============sum of costs JUST for fuelings payed in PLN=========
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN (Expedition expedition){
        List<Fuel> listOfFuelingsPayedInPLN = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, CurrencyCode.PLN);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (int i = 0; i < listOfFuelingsPayedInPLN.size(); i++) {
            BigDecimal costOfSingleFueling = listOfFuelingsPayedInPLN.get(i).getCost();
            sumOfCosts = sumOfCosts.add(costOfSingleFueling);
        }
        return sumOfCosts;

    }

    //=============sum of costs for fuelings payed in PLN OR other choosen currency=====
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOf(CurrencyCode currencyCode, Expedition expedition) {
        List<Fuel> listOfFuelingsWithGivenCurrencyCode = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);

        BigDecimal sumOfCosts=new BigDecimal(0.0);
        if(currencyCode.equals(CurrencyCode.PLN)) {
                sumOfCosts = calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        }else {
            calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(currencyCode, expedition);
        }
        return sumOfCosts;
    }

    //=============sum of costs for fuelings payed in ALL foreign currencies together==============
    @Override
    public BigDecimal calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(Expedition expedition){
        List<Fuel> listOFFuelingsFromGivenExpedition = fuelRepository.findFuelsByExpedition(expedition);
        List<Fuel> listOfFuelingsFromGivenExpeditionPayedInForeignCurrency = listOFFuelingsFromGivenExpedition.stream()
                .filter(fuel -> !fuel.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());

        BigDecimal latestCurrencyExchangeRate;
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (int i =0; i<listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.size(); i++){
            BigDecimal costOfSingleFueling = listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.get(i).getCost();
            try {
                latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.get(i));
                sumOfCosts = sumOfCosts.add(costOfSingleFueling.multiply(latestCurrencyExchangeRate));
            } catch (IOException e) {
                throw new NoLatestCurrencyReachedException();
            }
        }
        return sumOfCosts;
    }
}
