package com.sda.groupa.shippingcostcalculator.journey.repository;


import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface JourneyRepository extends JpaRepository<Journey, Long> {
    List<Journey> findAllByExpedition(Expedition expedition);

    //@Query("select f,max(f.endDate) from Journey f where f.expedition.id = :id ORDER BY f.endDate")
    @Query("select f from Journey f  WHERE f.expedition.id = :id AND f.endDate in (select max(f.endDate) from Journey f where f.expedition.id = :id  ) ")
    List<Optional<Journey>> findJouByExpeditionAndFarthestDate(Long id);


/*    @Query("select f from FreightRate f  WHERE f.endDate = (select max(f.endDate) from FreightRate f where f.expedition.id = :id  ) ")
    List<Optional<FreightRate>> findFrightRateByExpeditionAndFarthestDate(Long id);*/

}
