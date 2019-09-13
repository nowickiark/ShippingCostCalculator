package com.sda.groupa.shippingcostcalculator.extraCosts.repository;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExtraCostRepository extends JpaRepository<ExtraCost, Long> {

    List<ExtraCost> findExtraCostByExpedition(Expedition expedition);

/*    @Query("select d from Driver d where d.user.username = :user")
    Optional<Driver> findDriverByUser(String user);*/

}
