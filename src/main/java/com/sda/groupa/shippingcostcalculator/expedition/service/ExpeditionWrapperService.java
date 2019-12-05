package com.sda.groupa.shippingcostcalculator.expedition.service;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.model.ExpeditionWrapper;
import com.sda.groupa.shippingcostcalculator.freightRate.service.FreightRateService;
import com.sda.groupa.shippingcostcalculator.journey.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class ExpeditionWrapperService {

    private final ExpeditionService expeditionService;
    private final FreightRateService freightRateService;
    private final JourneyService journeyService;

    @Autowired
    public ExpeditionWrapperService(ExpeditionService expeditionService, FreightRateService freightRateService, JourneyService journeyService){
        this.expeditionService = expeditionService;
        this.freightRateService = freightRateService;
        this.journeyService = journeyService;
    }

    public List<ExpeditionWrapper> getCurrentExpeditionWrappers(){
        List<Expedition> currentExpeditions = expeditionService.findOpenExpeditions();
        List<ExpeditionWrapper> currentWrappers = currentExpeditions.stream().map(this::transformExpeditionToExpeditionWrapper).collect(Collectors.toList());
        return currentWrappers;
    }

    private ExpeditionWrapper transformExpeditionToExpeditionWrapper(Expedition expedition){
        final Long expeditionId = expedition.getId();
        ExpeditionWrapper expeditionWrapper = ExpeditionWrapper.builder()
                        .expedition(expedition)
                        .freightRate(freightRateService.getlastFreightRateByExpedition(expeditionId))
                        .journey(journeyService.getlastJourneyByExpedition(expeditionId))
                        .averageMilage(1.2)
                        .onRoad(true)
                        .build();
        return expeditionWrapper;
    }


}
