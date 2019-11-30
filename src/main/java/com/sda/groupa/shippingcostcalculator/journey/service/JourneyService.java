package com.sda.groupa.shippingcostcalculator.journey.service;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import com.sda.groupa.shippingcostcalculator.journey.repository.JourneyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public void addJourney(Journey journey){
        journeyRepository.save(journey);
    }

    public List<Journey> findAll(){
        return journeyRepository.findAll();
    }

    public void updateJourney(Journey journey){
        journeyRepository.save(journey);
    }

    public Optional<Journey> findById(Long id){
        return journeyRepository.findById(id);
    }
    public List<Journey> findByExpedition(Expedition expedition){
        return journeyRepository.findAllByExpedition(expedition);
    }

    public Journey getlastJourneyByExpedition(long expeditionId){
        Journey journey = null;
        List<Optional<Journey>> jouByExpeditionAndFarthestDate = journeyRepository.findJouByExpeditionAndFarthestDate(expeditionId);

        if(jouByExpeditionAndFarthestDate.size() > 0){
            journey = jouByExpeditionAndFarthestDate.get(0).get();
        }

        return journey;
    }

}