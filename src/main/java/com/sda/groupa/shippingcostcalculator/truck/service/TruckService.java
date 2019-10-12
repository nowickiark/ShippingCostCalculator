package com.sda.groupa.shippingcostcalculator.truck.service;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import com.sda.groupa.shippingcostcalculator.truck.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckService {

    public final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public void saveTruck(Truck truck){

        truckRepository.save(truck);
    }

    public Optional<Truck> getTruck(Long id){
        Optional<Truck> truck = truckRepository.findById(id);
        return truck;
    }

    public List<Truck> getTrucks(){
        List<Truck> trucks = truckRepository.findAll();
        return trucks;
    }

    public Optional<Truck> getTruckById(Long id){

        Optional<Truck> truckOptional = truckRepository.findById(id);

        return truckOptional;

    }

}
