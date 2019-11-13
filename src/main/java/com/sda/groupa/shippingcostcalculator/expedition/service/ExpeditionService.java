package com.sda.groupa.shippingcostcalculator.expedition.service;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ExpeditionService {

   private final ExpeditionRepository expeditionRepository;

   @Autowired
    public ExpeditionService(ExpeditionRepository expeditionRepository){
       this.expeditionRepository = expeditionRepository;
   }

   public void addExpedition(Expedition expedition){
       expeditionRepository.save(expedition);
   }

   public List<Expedition> getExpeditions(){
       return expeditionRepository.findAll();
   }

   public Optional<Expedition> getExpeditionById(Long id){
       return expeditionRepository.findById(id);
   }

   public List<Expedition> findExpeditionsByDriver(Driver driver){return expeditionRepository.findExpeditionsByDriver(driver);}

   public List<Expedition> findCurrentExpeditions(){return expeditionRepository.findExpeditionsByEndDayIsNull();}

   public List<Expedition> findOpenExpeditions(){return expeditionRepository.findExpeditionsByClosingDateIsNull();}

   public List<Expedition> findExpeditionsByDriverId(Long driverId){return expeditionRepository.findExpeditionsByDriverId(driverId);}

   public Long countKilometersTravelled(Expedition expedition){
       return expedition.getEndOdometerReading()-expedition.getStartOdometerReading();
   }

   public Long countDurationOfExpedition(Expedition expedition){
       return ChronoUnit.DAYS.between(expedition.getStartDay(), expedition.getEndDay());
   }
}
