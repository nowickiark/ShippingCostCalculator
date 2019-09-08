package com.sda.groupa.shippingcostcalculator.truck.repository;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck,Long> {
}
