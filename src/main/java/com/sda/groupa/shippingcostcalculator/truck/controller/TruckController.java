package com.sda.groupa.shippingcostcalculator.truck.controller;

import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import com.sda.groupa.shippingcostcalculator.truck.service.TruckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/truckList")
    public String getTrucks(Model model){
        List<Truck> trucks = truckService.getTrucks();
        model.addAttribute("trucks",trucks);
        return "truck-list";
    }

    @GetMapping("/truck/add")
        public String addTruck(Model model){
        Truck truck = new Truck();
        model.addAttribute("truck",truck);
        return "truck-add";
    }

    @GetMapping("/truck/add/{id}")
    public String addTruck(Model model,@PathVariable Long id){
        Truck truck = truckService.getTruckById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        model.addAttribute("truck",truck);
        return "truck-add";
    }

    @PostMapping("/truck/add")
    public String addTruck(@ModelAttribute Truck truck){
        truckService.saveTruck(truck);
        return "redirect:/truckList";
    }


}
