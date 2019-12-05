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

    public BigDecimal sumOfFreightRatesForExpedition(Long expeditionId){
        List<FreightRate> freightRatesByExpedition = freightRateRepository.getFreightRatesByExpeditionId(expeditionId);
        BigDecimal sumOfCosts=new BigDecimal(0.0);
        for(FreightRate singleFreightCost:freightRatesByExpedition){
            if(singleFreightCost.getCurrencyCode().equals(CurrencyCode.PLN)){
                sumOfCosts = sumOfCosts.add(singleFreightCost.getAmount());
            } else {
                sumOfCosts = sumOfCosts.add(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(singleFreightCost.getAmount(),singleFreightCost.getCurrencyCode(),singleFreightCost.getDate()));
            }
        }
        return sumOfCosts;
    }

    public FreightRate getlastFreightRateByExpedition(Long expeditionId){
        FreightRate freightRate = null;
        List<Optional<FreightRate>> freightRateList = freightRateRepository.findFrightRateByExpeditionAndFarthestDate(expeditionId);
        if(freightRateList.size() >0){
            freightRate = freightRateList.get(0).get();
        }
        return freightRate;
    }


}
