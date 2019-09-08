package com.sda.groupa.shippingcostcalculator.expedition.repository;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpeditionRepository extends JpaRepository<Expedition, Long> {
}
