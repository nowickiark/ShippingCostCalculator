package com.sda.groupa.shippingcostcalculator.journey.repository;


import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JourneyRepository extends JpaRepository<Journey, Long> {

}
