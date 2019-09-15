package com.sda.groupa.shippingcostcalculator.truckParts.service;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import com.sda.groupa.shippingcostcalculator.truckParts.repository.TruckPartsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckPartsService {
    private TruckPartsRepository truckPartsRepository;

    public TruckPartsService(TruckPartsRepository truckPartsRepository) {
        this.truckPartsRepository = truckPartsRepository;
    }

    public void addTruckParts(TruckParts truckParts) {
        truckPartsRepository.save(truckParts);
    }

    public Optional<TruckParts> getById(Long id) {
        return truckPartsRepository.findById(id) ;
    }

    public List<TruckParts> getTruckParts(){
        List<TruckParts> truckParts = truckPartsRepository.findAll();

        return truckParts;
    }

    public List<TruckParts> getTruckPartsByExpeditionId(Expedition expedition){
        return truckPartsRepository.findTruckPartsByExpedition(expedition);
    }

}
