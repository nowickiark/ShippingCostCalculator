package com.sda.groupa.shippingcostcalculator.journey.controller;

import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import com.sda.groupa.shippingcostcalculator.journey.service.JourneyService;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JourneyController {

    private final JourneyService journeyService;
    private final ExpeditionService expeditionService;

    public JourneyController(JourneyService journeyService, ExpeditionService expeditionService) {
        this.journeyService = journeyService;
        this.expeditionService = expeditionService;
    }

    @GetMapping(value = "/journeys")
    public ModelAndView getJourneys() {
        List<Journey> listOfJourneys = journeyService.findAll();
        ModelAndView modelAndView = new ModelAndView("journey");
        modelAndView.addObject("listOfJourneys", listOfJourneys);
        return modelAndView;
    }

    @PostMapping(value = "/journey/add")
    public String addJourney(@ModelAttribute Journey journey) {
        journeyService.addJourney(journey);
        return "redirect:/journeys";
    }

    @GetMapping(value = "/journey/add")
    public ModelAndView addJourneyPage() {
        ModelAndView modelAndView = new ModelAndView("addJourney");
        modelAndView.addObject("journey", new Journey());
        return modelAndView;
    }

    @PostMapping(value = "/journey/update")
    public String updateJourney(@ModelAttribute Journey journey) {
        journeyService.updateJourney(journey);
        return "redirect:/drivers";
    }

    @GetMapping(value = "/journey/update/{id}")
    public ModelAndView updateJourneyPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("addJourney");
        modelAndView.addObject("journey", journeyService.findById(id));
        modelAndView.addObject("update", true);
        return modelAndView;
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/{id}/addJourney")
    public String showAddPageOfJourneysByExpedition (Model model, @PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<Journey> journeysList = Lists.reverse(journeyService.findByExpedition(expedition));
        Journey journey = new Journey();
        journey.setExpedition(expedition);
        model.addAttribute("journeysList",journeysList);
        model.addAttribute("newJourney",journey);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "journey-list-add";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/addJourney/{id}")
    public String showEditPageOfJourneysByExpedition (Model model, @PathVariable("id") Long id ){
        Journey journey = journeyService.findById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        Expedition expedition = journey.getExpedition();
        List<Journey> journeysList = Lists.reverse(journeyService.findByExpedition(expedition));
        model.addAttribute("journeysList",journeysList);
        model.addAttribute("newJourney",journey);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "journey-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addJourney")
    public String addExtraCostToExpedition(@ModelAttribute Journey journey){
        journeyService.addJourney(journey);
        return "redirect:/expedition/" + journey.getExpedition().getId() + "/addJourney";
    }
}



