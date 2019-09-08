package com.sda.groupa.shippingcostcalculator.expedition.controller;

import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import com.sda.groupa.shippingcostcalculator.truck.service.TruckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpeditionController {

    private ExpeditionService expeditionService;
    private TruckService truckService;

    public ExpeditionController(ExpeditionService expeditionService, TruckService truckService) {
        this.expeditionService = expeditionService;
        this.truckService = truckService;
    }

    @GetMapping("/expedition/Add")
    public ModelAndView getExpeditionform(){
        ModelAndView modelAndView = new ModelAndView("expedition");

        Expedition expedition = new Expedition();

        List<Truck> trucks = truckService.getTrucks();

        modelAndView.addObject("expedition",expedition);
        modelAndView.addObject("trucks",trucks);

        return modelAndView;
    }

    @GetMapping("/expeditions")
    public ModelAndView getExpeditions(){
            ModelAndView modelAndView = new ModelAndView("expeditions");

            List<Expedition> expeditions = expeditionService.getExpeditions();

            modelAndView.addObject("expeditions",expeditions);
            return modelAndView;
    }

    @PostMapping("/expedition/Add")
    public ModelAndView addExpedition(@ModelAttribute Expedition expedition){

        expeditionService.addExpedition(expedition);

        Expedition expedition1 = expeditionService.getExpeditionById(expedition.getId()).get();

        System.out.println(expedition.getId());

        return new ModelAndView("redirect:/expeditions");

        /*return "redirect:/expeditions";*/

    }


}
