package com.sda.groupa.shippingcostcalculator.extraCosts.service;

import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.repository.ExtraCostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraCostService {

    private ExtraCostRepository extraCostRepository;

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



}
