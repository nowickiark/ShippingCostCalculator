package com.sda.groupa.shippingcostcalculator.fuel.fuelService;



import com.sda.groupa.shippingcostcalculator.costCalculator.CostCalculator;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.repository.CurrencyExchangeRatesRepository;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.fuel.exception.NoLatestCurrencyExceptionReached;
import com.sda.groupa.shippingcostcalculator.fuel.exception.ProblemWithJsonParsingException;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuel.fuelRepository.FuelRepository;
import org.json.JSONException;
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


    private BigDecimal calculateSumOfCostsInCurrencyOtherThanPLN(CurrencyCode currencyCode, Expedition expedition) {


        List<Fuel> listOfFuelingsWithOtherCurrencyCodes = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);

        BigDecimal latestCurrencyExchangeRate = new BigDecimal(0.0);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for(int i=0; i<listOfFuelingsWithOtherCurrencyCodes.size(); i++){
            BigDecimal costOfSingleFueling = listOfFuelingsWithOtherCurrencyCodes.get(i).getCost();
            try {
                latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(listOfFuelingsWithOtherCurrencyCodes.get(i));
            } catch (IOException e) {
                throw new NoLatestCurrencyExceptionReached();   //nie wyświeltlać stack traca!!!
            } catch (JSONException e) {
                throw new ProblemWithJsonParsingException();    //nie wyświeltlać stack traca!!!
            }
            sumOfCosts = sumOfCosts.add(costOfSingleFueling.multiply(latestCurrencyExchangeRate));
        }
        return sumOfCosts;
    }

    @Override
    public BigDecimal calculateSumOfCostsInCurrencyOf(CurrencyCode currencyCode, Expedition expedition) {
        List<Fuel> listOfFuelingsWithGivenCurrencyCode = fuelRepository.findFuelsByExpeditionAndAndCurrencyCode(expedition, currencyCode);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        if(currencyCode.equals(CurrencyCode.PLN)) {
            for (int i = 0; i < listOfFuelingsWithGivenCurrencyCode.size(); i++) {
                BigDecimal costOfSingleFueling = listOfFuelingsWithGivenCurrencyCode.get(i).getCost();
                sumOfCosts = sumOfCosts.add(costOfSingleFueling);
            }
        }else {
            calculateSumOfCostsInCurrencyOtherThanPLN(currencyCode, expedition);
        }
        return sumOfCosts;
    }


    //====need to be finished====
    public BigDecimal calculateSumOfCostsInAllCurrenciesOtherThanPLN(Expedition expedition){
        List<Fuel> listOFFuelingsFromGivenExpedition;

        return null;
    }
}
