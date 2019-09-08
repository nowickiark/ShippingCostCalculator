package com.sda.groupa.shippingcostcalculator.driverRepository;

import com.sda.groupa.shippingcostcalculator.driverModel.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Long> {
}
