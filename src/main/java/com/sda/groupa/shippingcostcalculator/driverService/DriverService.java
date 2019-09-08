package com.sda.groupa.shippingcostcalculator.driverService;

import com.sda.groupa.shippingcostcalculator.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driverRepository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void addDriver(Driver driver){
        driverRepository.save(driver);
    }

    public List<Driver> findAll(){
        return driverRepository.findAll();
    }

    public void updateDriver(Driver driver){
        driverRepository.save(driver);
    }

    public Optional<Driver> findById(Long id){
        return driverRepository.findById(id);
    }
}






