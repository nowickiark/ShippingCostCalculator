package com.sda.groupa.shippingcostcalculator.fuel.fuelRepository;


import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

    List<Fuel> findFuelsByExpedition(Expedition expedition);

}
