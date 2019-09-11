package com.sda.groupa.shippingcostcalculator.driver.driverRepository;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Long> {
}
