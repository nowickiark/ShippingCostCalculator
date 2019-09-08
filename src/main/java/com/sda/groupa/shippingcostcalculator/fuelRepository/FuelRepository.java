package com.sda.groupa.shippingcostcalculator.fuelRepository;

import com.sda.groupa.shippingcostcalculator.fuelModel.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuelRepository extends JpaRepository<Fuel, Long> {


}