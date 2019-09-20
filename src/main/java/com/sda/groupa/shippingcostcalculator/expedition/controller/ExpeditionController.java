package com.sda.groupa.shippingcostcalculator.expedition.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import com.sda.groupa.shippingcostcalculator.truck.model.Truck;
import com.sda.groupa.shippingcostcalculator.truck.service.TruckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpeditionController {

    private final ExpeditionService expeditionService;
    private final TruckService truckService;
    private final DriverStrategy driverStrategy;
    private final DriverService driverService;

    public ExpeditionController(ExpeditionService expeditionService, TruckService truckService, DriverStrategy driverStrategy, DriverService driverService) {
        this.expeditionService = expeditionService;
        this.truckService = truckService;
        this.driverStrategy = driverStrategy;
        this.driverService = driverService;
    }

    @GetMapping("/expedition/add")
    public ModelAndView getExpeditionform(){
        ModelAndView modelAndView = new ModelAndView("expedition");
        Expedition expedition = new Expedition();
        modelAndView.addObject("expedition",expedition);
        modelAndView.addObject("trucks",truckService.getTrucks());
        modelAndView.addObject("drivers",driverService.findAll());
        return modelAndView;
    }


    @GetMapping("/expedition/add/{id}")
    public ModelAndView getExpeditionform(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("expedition");
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<Truck> trucks = truckService.getTrucks();
        modelAndView.addObject("expedition",expedition);
        modelAndView.addObject("trucks",trucks);
        return modelAndView;
    }

    @GetMapping("/expeditions")
    public ModelAndView getExpeditions(){
        ModelAndView modelAndView = new ModelAndView("expeditions");
        Driver driver = driverStrategy.getDriver();
        List<Expedition> expeditions = expeditionService.findExpeditionsByDriver(driver);
        modelAndView.addObject("expeditions",expeditions);
        return modelAndView;
    }

    @GetMapping("expeditions/all")
    public ModelAndView getAllExpeditions(){
        ModelAndView modelAndView = new ModelAndView("spedytorHome");
        modelAndView.addObject("expeditions",expeditionService.getExpeditions());
        return modelAndView;
    }

    @GetMapping("expeditions/driver")
    public ModelAndView getDriversExpeditions(@ModelAttribute Driver driver){
        ModelAndView modelAndView = new ModelAndView("spedytorHome");
        modelAndView.addObject("expeditions",expeditionService.findExpeditionsByDriver(driver));
        modelAndView.addObject("drivers",driverService.findAll());
        return modelAndView;
    }

    @GetMapping("expeditions/current")
    public ModelAndView getCurrentExpeditions(){
        ModelAndView modelAndView = new ModelAndView("spedytorHome");
        modelAndView.addObject("expeditions",expeditionService.findCurrentExpeditions());
        return modelAndView;
    }


    @PostMapping("/expedition/add")
    public ModelAndView addExpedition(@ModelAttribute Expedition expedition){
        Driver driver = expedition.getDriver();
        driver.setExpedition(expedition);
        driverService.addDriver(driver);
        return new ModelAndView("redirect:/expeditions");
    }

}
