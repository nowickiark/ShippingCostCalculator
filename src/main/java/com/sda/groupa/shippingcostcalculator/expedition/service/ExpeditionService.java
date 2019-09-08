package com.sda.groupa.shippingcostcalculator.expedition.service;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
