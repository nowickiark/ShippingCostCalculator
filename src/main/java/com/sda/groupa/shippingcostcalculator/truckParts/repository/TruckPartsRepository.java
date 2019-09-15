package com.sda.groupa.shippingcostcalculator.truckParts.repository;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckPartsRepository extends JpaRepository<TruckParts, Long> {
    List<TruckParts> findTruckPartsByExpedition(Expedition expedition);
}
