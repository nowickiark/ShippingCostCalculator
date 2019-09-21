package com.sda.groupa.shippingcostcalculator.expeditionSummary.repository;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expeditionSummary.model.ExpeditionSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpeditionSummaryRepository extends JpaRepository<ExpeditionSummary, Long> {
    ExpeditionSummary findByExpeditionId(Long id);

}
