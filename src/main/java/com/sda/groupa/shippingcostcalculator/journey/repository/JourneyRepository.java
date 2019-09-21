package com.sda.groupa.shippingcostcalculator.journey.repository;


import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JourneyRepository extends JpaRepository<Journey, Long> {
    List<Journey> findAllByExpedition(Expedition expedition);

}
