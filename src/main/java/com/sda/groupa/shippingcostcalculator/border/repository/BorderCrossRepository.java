package com.sda.groupa.shippingcostcalculator.border.repository;

import com.sda.groupa.shippingcostcalculator.border.model.BorderCross;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorderCrossRepository extends JpaRepository<BorderCross, Long> {

    List<BorderCross> findBorderCrossesByExpedition(Expedition expedition);

}
