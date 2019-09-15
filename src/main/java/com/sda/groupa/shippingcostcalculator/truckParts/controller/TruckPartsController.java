package com.sda.groupa.shippingcostcalculator.truckParts.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import com.sda.groupa.shippingcostcalculator.truckParts.service.TruckPartsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class TruckPartsController {
    private final TruckPartsService truckPartsService;
    private final DriverStrategy driverStrategy;

    public TruckPartsController(TruckPartsService truckPartsService, DriverStrategy driverStrategy) {
        this.truckPartsService = truckPartsService;
        this.driverStrategy = driverStrategy;
    }

    @GetMapping("/truckparts/add")
    public ModelAndView getFormPage() {

        TruckParts truckParts = new TruckParts();

        ModelAndView modelAndView = new ModelAndView("truckparts");
        modelAndView.addObject("truckparts", truckParts);
        modelAndView.addObject("currencyCodeType", CurrencyCode.values());
        return modelAndView;
    }

    @GetMapping("/truckparts/list")
    public ModelAndView getTruckParts(){

        ModelAndView modelAndView = new ModelAndView("truckpartslist");
        List<TruckParts> truckParts = truckPartsService.getTruckParts();
        modelAndView.addObject("truckparts", truckParts);
        return modelAndView;

    }

    @GetMapping("/expedition/listOfTruckParts")
    public ModelAndView getTruckPartsByExpedition(){

        Driver driver = driverStrategy.getDriver();

        ModelAndView modelAndView = new ModelAndView("truckpartslist");
        List<TruckParts> truckParts = truckPartsService.getTruckPartsByExpeditionId(driver.getExpedition());
        modelAndView.addObject("truckparts",truckParts);
        return modelAndView;

    }

    @GetMapping("/truckparts/add/{id}")
    public ModelAndView getTruckPartsForm(@PathVariable Long id) {

        Optional<TruckParts> truckParts = truckPartsService.getById(id);
        ModelAndView modelAndView = new ModelAndView("truckparts");
        modelAndView.addObject("truckparts", truckParts);
        return modelAndView;
    }

    @PostMapping("/truckparts/add")
    public String truckParts (@ModelAttribute TruckParts truckParts) {
        Driver driver = driverStrategy.getDriver();
        truckParts.setExpedition(driver.getExpedition());
        truckPartsService.addTruckParts(truckParts);
        return "redirect:/expedition/listOfTruckParts";
    }

}




