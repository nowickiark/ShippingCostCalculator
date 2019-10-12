package com.sda.groupa.shippingcostcalculator.freightRate.repository;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreightRateRepository extends JpaRepository<FreightRate, Long> {

   List<FreightRate> getFreightRatesByExpeditionId(Long id);

}
