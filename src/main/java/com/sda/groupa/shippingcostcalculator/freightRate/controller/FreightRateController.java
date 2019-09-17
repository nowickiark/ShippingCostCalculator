package com.sda.groupa.shippingcostcalculator.freightRate.controller;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import com.sda.groupa.shippingcostcalculator.freightRate.service.FreightRateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class FreightRateController {

    FreightRateService freightRateService;
    ExpeditionService expeditionService;

    public FreightRateController(FreightRateService freightRateService, ExpeditionService expeditionService) {
        this.freightRateService = freightRateService;
        this.expeditionService = expeditionService;
    }

    @GetMapping("/freightRateList")
    public ModelAndView getFrightRateList(){
        ModelAndView modelAndView = new ModelAndView("freightRateList");
        modelAndView.addObject("freightRates",freightRateService.getAllFreightRates());
        return modelAndView;
    }



    @GetMapping("/expedition/freightRateList/{expeditionId}")
    public ModelAndView getFreightRateListByExpedition(@PathVariable Long expeditionId){
        ModelAndView modelAndView = new ModelAndView("freightRateList");
        Expedition expedition = expeditionService.getExpeditionById(expeditionId).orElseThrow(() -> new RuntimeException("Unavailable"));
        modelAndView.addObject("freightRates",freightRateService.findFreightRatesByExpedition(expedition));
        return modelAndView;
    }

    @GetMapping("/freightRate/add")
    public ModelAndView showFreightRateForm(){
        ModelAndView modelAndView = new ModelAndView("addFreightRate");
        FreightRate freightRate = new FreightRate();
        List<Expedition> expeditions = expeditionService.findCurrentExpeditions();
        modelAndView.addObject("freightRate",freightRate);
        modelAndView.addObject("expeditions",expeditions);
        modelAndView.addObject("currencyCodeType", CurrencyCode.values());
        return modelAndView;
    }

    @PostMapping("/freightRate/add")
    public String addFreightrate(@ModelAttribute FreightRate freightRate){
        freightRateService.saveFreightRate(freightRate);
        return "redirect:/spedytorHome";
    }



}
