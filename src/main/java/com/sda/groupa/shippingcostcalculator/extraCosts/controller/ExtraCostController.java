package com.sda.groupa.shippingcostcalculator.extraCosts.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.login.workerStrategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ExtraCostController {
    private final ExtraCostService extraCostService;
    private final DriverStrategy driverStrategy;
    private final DriverService driverService;

    public ExtraCostController(ExtraCostService extraCostService, DriverStrategy driverStrategy, DriverService driverService) {
        this.extraCostService = extraCostService;
        this.driverStrategy = driverStrategy;
        this.driverService = driverService;
    }

    @GetMapping("/extracost/add")
    public ModelAndView getFormPage() {

        ExtraCost extraCost = new ExtraCost();

        ModelAndView modelAndView = new ModelAndView("extracost");
        modelAndView.addObject("extracost", extraCost);
        return modelAndView;
    }

    @GetMapping("/extracosts/list")
    public ModelAndView getExtraCosts(){

        ModelAndView modelAndView = new ModelAndView("extracostslist");
        List<ExtraCost> extraCosts = extraCostService.getExtraCosts();
        modelAndView.addObject("extracosts",extraCosts);
        return modelAndView;

    }

    @GetMapping("/expedition/listOfExtraCosts")
    public ModelAndView getExtraCostsByExpedition(){

        Expedition expedition = driverStrategy.getExpedition();

        ModelAndView modelAndView = new ModelAndView("extracostslist");
        List<ExtraCost> extraCosts = extraCostService.getExtraCostsByExpetionId(expedition);
        modelAndView.addObject("extracosts",extraCosts);
        return modelAndView;
    }

    @GetMapping("/extracost/add/{id}")
    public ModelAndView getExtraCostsForm(@PathVariable Long id) {

        Optional<ExtraCost> extraCost = extraCostService.getById(id);
        ModelAndView modelAndView = new ModelAndView("extracost");
        modelAndView.addObject("extracost", extraCost);
        return modelAndView;
    }

    @PostMapping("/extracost/add")
    public String extraCosts (@ModelAttribute ExtraCost extracost) {

        Expedition expedition = driverStrategy.getExpedition();

        extracost.setExpedition(expedition);

        extraCostService.addExtraCost(extracost);
        return "redirect:/expedition/listOfExtraCosts";
    }

}
