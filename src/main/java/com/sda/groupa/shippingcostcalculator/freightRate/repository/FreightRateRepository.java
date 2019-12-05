package com.sda.groupa.shippingcostcalculator.freightRate.repository;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FreightRateRepository extends JpaRepository<FreightRate, Long> {

   List<FreightRate> getFreightRatesByExpeditionId(Long id);

   //@Query("select d from Driver d where d.user.username = :user")
   //Optional<Driver> findDriverByUsername(String user);

   //@Query("select f,max(f.endDate) from FreightRate f where f.expedition.id = :id ORDER BY f.endDate")
  @Query("select f from FreightRate f  WHERE f.expedition.id = ?1 AND f.endDate = (select max(f.endDate) from FreightRate f where f.expedition.id = ?1  ) ")
  List<Optional<FreightRate>> findFrightRateByExpeditionAndFarthestDate(Long id);
}
