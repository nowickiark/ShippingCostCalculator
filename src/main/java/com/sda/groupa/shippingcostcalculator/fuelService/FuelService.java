package com.sda.groupa.shippingcostcalculator.fuelService;


import com.sda.groupa.shippingcostcalculator.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuelRepository.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Fuel> getListOfFuellings(){
        return fuelRepository.findAll();
    }

    public void updateFueling(Fuel fuel){
        fuelRepository.findById(fuel.getId()).ifPresent(f->fuelRepository.delete(f));
        fuelRepository.save(fuel);
    }

}
