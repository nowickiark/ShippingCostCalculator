package com.sda.groupa.shippingcostcalculator.freightRate.service;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import com.sda.groupa.shippingcostcalculator.freightRate.repository.FreightRateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FreightRateService {

    private final FreightRateRepository freightRateRepository;
    private final CurrencyRateService currencyRateService;

    public FreightRateService(FreightRateRepository freightRateRepository, CurrencyRateService currencyRateService) {
        this.freightRateRepository = freightRateRepository;
        this.currencyRateService = currencyRateService;
    }

    public List<FreightRate> getAllFreightRates(){
        return freightRateRepository.findAll();
    }

    public List<FreightRate> findFreightRatesByExpedition(Expedition expedition){
        return freightRateRepository.getFreightRatesByExpeditionId(expedition.getId());
    }

    public void saveFreightRate(FreightRate freightRate){
        freightRateRepository.save(freightRate);
    }

    public Optional<FreightRate> getFreightrateById(Long id){return freightRateRepository.findById(id);}

    public BigDecimal sumOfFreightRatesForExpedition(Expedition expedition){
        List<FreightRate> freightRatesByExpedition = findFreightRatesByExpedition(expedition);
        List<FreightRate> listOfFreightCostssFromGivenExpeditionPayedInForeignCurrency = freightRatesByExpedition.stream()
                .filter(fR -> !fR.getCurrencyCode().equals(CurrencyCode.PLN)).collect(Collectors.toList());
        BigDecimal latestCurrencyExchangeRate;
        BigDecimal sumOfCosts=new BigDecimal(0.0);

        for(FreightRate singleFreightCostWithForignCurreny:listOfFreightCostssFromGivenExpeditionPayedInForeignCurrency){
            BigDecimal valueOfSingleFreightRate = singleFreightCostWithForignCurreny.getAmount();
/*            try {
                latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(singleFreightCostWithForignCurreny.getCurrencyCode());
            }*/
        }

        return null;

    }
/*
            for (int i =0; i<listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.size(); i++){
        BigDecimal costOfSingleFueling = listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.get(i).getCost();
        try {
            latestCurrencyExchangeRate = currencyRateService.getLatestCurrencyExchangeRate(listOfFuelingsFromGivenExpeditionPayedInForeignCurrency.get(i));
            sumOfCosts = sumOfCosts.add(costOfSingleFueling.multiply(latestCurrencyExchangeRate));
        } catch (IOException e) {
            throw  new NoLatestCurrencyReachedException();
        }
    }*/

}
