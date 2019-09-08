package com.sda.groupa.shippingcostcalculator.extraCosts.repository;

import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraCostRepository extends JpaRepository<ExtraCost, Long> {
}
