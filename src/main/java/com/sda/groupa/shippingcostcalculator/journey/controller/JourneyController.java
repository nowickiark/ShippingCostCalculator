package com.sda.groupa.shippingcostcalculator.journey.controller;

import com.sda.groupa.shippingcostcalculator.journey.model.Journey;
import com.sda.groupa.shippingcostcalculator.journey.service.JourneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
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
}



