package com.sda.groupa.shippingcostcalculator.freightRate.service;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import com.sda.groupa.shippingcostcalculator.freightRate.repository.FreightRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreightRateService {

    private FreightRateRepository freightRateRepository;

    public FreightRateService(FreightRateRepository freightRateRepository) {
        this.freightRateRepository = freightRateRepository;
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

}
