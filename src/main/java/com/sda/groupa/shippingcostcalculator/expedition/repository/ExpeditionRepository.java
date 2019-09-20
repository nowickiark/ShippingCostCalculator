package com.sda.groupa.shippingcostcalculator.expedition.repository;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpeditionRepository extends JpaRepository<Expedition, Long> {

    List<Expedition> findExpeditionsByDriver(Driver driver);

    List<Expedition> findExpeditionsByDriverId(Long driverId);

    List<Expedition> findExpeditionsByEndDayIsNull();

}
