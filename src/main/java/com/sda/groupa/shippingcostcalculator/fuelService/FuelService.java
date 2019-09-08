package com.sda.groupa.shippingcostcalculator.fuelService;


import com.sda.groupa.shippingcostcalculator.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuelRepository.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;

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
        fuelRepository.findById(fuel.getId()).ifPresent(f->fuelRepository.delete(f));
        fuelRepository.save(fuel);
    }

    public Optional<Fuel> findById(Long id){
        return fuelRepository.findById(id);
    }

}
