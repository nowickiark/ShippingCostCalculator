package com.sda.groupa.shippingcostcalculator.truck.controller;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import com.sda.groupa.shippingcostcalculator.truck.service.TruckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class TruckController {

    private TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/truckList")
    public ModelAndView getTrucks(){
        ModelAndView modelAndView = new ModelAndView("truckList");

        List<Truck> trucks = truckService.getTrucks();

        modelAndView.addObject("trucks",trucks);

        return modelAndView;
    }

    @GetMapping("/truck/Add")
        public ModelAndView addTruck(){
        ModelAndView modelAndView = new ModelAndView("truck");

        Truck truck = new Truck();

        modelAndView.addObject(truck);

        return modelAndView;
    }


    @GetMapping("/truck/Add/{id}")
    public ModelAndView addTruck(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("truck");

        Optional<Truck> truck = truckService.getTruckById(id);

        modelAndView.addObject("truck",truck);

        return modelAndView;
    }

    @PostMapping("/truck/Add")
    public String addTruck(@ModelAttribute Truck truck){

        truckService.saveTruck(truck);

        return "redirect:/truckList";
    }


}
