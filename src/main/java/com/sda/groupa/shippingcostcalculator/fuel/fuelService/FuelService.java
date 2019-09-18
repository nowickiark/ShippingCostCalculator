package com.sda.groupa.shippingcostcalculator.fuel.fuelService;

import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuel.fuelRepository.FuelRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelService implements CostCalculator {

    private final FuelRepository fuelRepository;

    private CurrencyRateService currencyRateService;

    public FuelService(FuelRepository fuelRepository, CurrencyRateService currencyRateService){
        this.fuelRepository = fuelRepository;
        this.currencyRateService = currencyRateService;
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
    //======================CALCULATIONS FOR COSTS OF FUELINGS================================
    //==== calculates sum of costs of fuelings payed in a choosen currency other than PLN======
    private BigDecimal calculateSumOfCostsInChoosenCurrencyAndOtherThanPLN(CurrencyCode currencyCode, Expedition expedition) {
        List<Fuel> listOfFuelingsWithGivenCurrencyCode = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for(Fuel fueling : listOfFuelingsWithGivenCurrencyCode){
            BigDecimal costOfSingleFueling = fueling.getCost();
            LocalDate dateOfPayment =fueling.getDateOfFueling();
            sumOfCosts = sumOfCosts.add(costOfSingleFueling.multiply(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleFueling,currencyCode,dateOfPayment)));
        }
        return sumOfCosts;
    }

    //=============sum of costs JUST for fuelings payed in PLN=========
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN (Expedition expedition){
        List<Fuel> listOfFuelingsPayedInPLN = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, CurrencyCode.PLN);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (Fuel fueling : listOfFuelingsPayedInPLN) {
            BigDecimal costOfSingleFueling = fueling.getCost();
            sumOfCosts = sumOfCosts.add(costOfSingleFueling);
        }
        return sumOfCosts;
    }

    //=============sum of costs for fuelings payed in PLN OR other choosen currency=====
    @Override
    public BigDecimal calculateSumOfCostsPayedInCurrencyOf(CurrencyCode currencyCode, Expedition expedition) {
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

        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for (Fuel fueling : listOfFuelingsFromGivenExpeditionPayedInForeignCurrency){
            BigDecimal costOfSingleFueling = fueling.getCost();
            CurrencyCode currencyCode = fueling.getCurrencyCode();
            LocalDate dateOfPayment = fueling.getDateOfFueling();
            BigDecimal costInPLN = currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(costOfSingleFueling, currencyCode, dateOfPayment);
            sumOfCosts = sumOfCosts.add(costInPLN);
        }
        return sumOfCosts;
    }
    //====sum of costs of ALL fuelings payed in ALL currencies=====
    @Override
    public BigDecimal calculateTotalCostsPayedInPLNandOtherCurrencies(Expedition expedition){
        BigDecimal fuelingsPayedInForeignCurrencies = calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        BigDecimal fuelingsPayedInPLN = calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        return fuelingsPayedInForeignCurrencies.add(fuelingsPayedInPLN);
    }

     //TODO calculateCostsInEURofFuelingsPayedInForeignCurrencies(),

    //======================CALCULATIONS FOR LITERS OF FUEL================================
    //===========calculate sum of liters in Poland=================
    public BigDecimal calculateSumOfLitersInPoland(Expedition expedition){
        List<Fuel> listOfFuelingsFromGivenExpedition = fuelRepository.findFuelsByExpedition(expedition);
        BigDecimal sumOfLitersInPoland = new BigDecimal(0.0);
        for(Fuel fuel : listOfFuelingsFromGivenExpedition){
            if(fuel.getCurrencyCode().equals(CurrencyCode.PLN)){
                BigDecimal litersFromSingleFueling = fuel.getLiters();
                sumOfLitersInPoland = sumOfLitersInPoland.add(litersFromSingleFueling);
            }
        }
        return sumOfLitersInPoland;
    }

    public BigDecimal calculateMediumPriceForOneLiterInPoland(Expedition expedition){
        BigDecimal costOfFuelingsInPoland = calculateSumOfCostsPayedInCurrencyOfPLN(expedition);
        BigDecimal litersInPoland = calculateSumOfLitersInPoland(expedition);
        return costOfFuelingsInPoland.divide(litersInPoland, RoundingMode.CEILING);
    }

    //===========calculate sum of liters abroad=================
    public BigDecimal calculateSumOfLitersAbroad(Expedition expedition){
        List<Fuel> listOfFuelingsFromGivenExpedition = fuelRepository.findFuelsByExpedition(expedition);
        BigDecimal sumOfLitersAbroad = new BigDecimal(0.0);
        for(Fuel fuel : listOfFuelingsFromGivenExpedition){
            if(!fuel.getCurrencyCode().equals(CurrencyCode.PLN)){
                BigDecimal litersFromSingleFueling = fuel.getLiters();
                sumOfLitersAbroad = sumOfLitersAbroad.add(litersFromSingleFueling);
            }
        }
        return sumOfLitersAbroad;
    }

    public BigDecimal calculateMediumPriceForOneLiterInAbroad(Expedition expedition){
        BigDecimal costOfFuelingsAbroad = calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(expedition);
        BigDecimal litersAbroad = calculateSumOfLitersAbroad(expedition);
        return costOfFuelingsAbroad.divide(litersAbroad, RoundingMode.CEILING);
    }

    //===========calculate sum of liters =================
    public BigDecimal calculateSumOfLiters(Expedition expedition){
        List<Fuel> listOfFuelingsFromGivenExpedition = fuelRepository.findFuelsByExpedition(expedition);
        BigDecimal sumOfLiters = new BigDecimal(0.0);
        for(Fuel fuel : listOfFuelingsFromGivenExpedition){
            BigDecimal litersFromSingleFueling = fuel.getLiters();
            sumOfLiters = sumOfLiters.add(litersFromSingleFueling);
        }
        return sumOfLiters;
    }

    public BigDecimal calculateMediumPriceForOneLiter(Expedition expedition){
        BigDecimal costOfFuelings = calculateTotalCostsPayedInPLNandOtherCurrencies(expedition);
        BigDecimal sumOfLiters = calculateSumOfLiters(expedition);
        return costOfFuelings.divide(sumOfLiters, RoundingMode.CEILING);
    }
}
