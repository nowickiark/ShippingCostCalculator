package com.sda.groupa.shippingcostcalculator.extraCosts.controller;

import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ExtraCostController {

    private final ExtraCostService extraCostService;
    private final DriverStrategy driverStrategy;
    private final DriverService driverService;
    private final CurrencyRateService currencyRateService;
    private final ExpeditionService expeditionService;


    public ExtraCostController(ExtraCostService extraCostService, DriverStrategy driverStrategy, DriverService driverService, CurrencyRateService currencyRateService, ExpeditionService expeditionService) {
        this.extraCostService = extraCostService;
        this.driverStrategy = driverStrategy;
        this.driverService = driverService;
        this.currencyRateService = currencyRateService;
        this.expeditionService = expeditionService;
    }

    @GetMapping("/extracost/add")
    public ModelAndView getFormPage() {
        ExtraCost extraCost = new ExtraCost();
        ModelAndView modelAndView = new ModelAndView("extracost");
        modelAndView.addObject("extracost", extraCost);
        modelAndView.addObject("currencyCodeType", CurrencyCode.values());
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
        List<ExtraCost> extraCosts = extraCostService.getExtraCostsByExpetion(expedition);
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
    public String extraCosts (@ModelAttribute ExtraCost extracost) throws IOException {
        Expedition expedition = driverStrategy.getExpedition();
        extracost.setExpedition(expedition);
        extraCostService.addExtraCost(extracost);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(extracost.getCurrencyCode(),extracost.getDateOfPurchase());
        return "redirect:/expedition/listOfExtraCosts";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/{id}/addExtraCost")
    public String showAddPageOfExtraCostsByExpedition (Model model, @PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<ExtraCost> extraCostsList = Lists.reverse(extraCostService.getExtraCostsByExpetion(expedition));
        ExtraCost extraCost = new ExtraCost();
        extraCost.setExpedition(expedition);
        model.addAttribute("extraCostsList",extraCostsList);
        model.addAttribute("newextraCost",extraCost);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "extraCost-list-add";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/addExtraCost/{id}")
    public String showEditPageOfExtraCostsByExpedition (Model model, @PathVariable("id") Long id ){
        ExtraCost extraCost = extraCostService.getById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        Expedition expedition = extraCost.getExpedition();
        List<ExtraCost> extraCostsList = Lists.reverse(extraCostService.getExtraCostsByExpetion(expedition));
        model.addAttribute("extraCostsList",extraCostsList);
        model.addAttribute("newextraCost",extraCost);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "extraCost-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addExtraCost")
    public String addExtraCostToExpedition(@ModelAttribute ExtraCost extraCost){
        extraCostService.addExtraCost(extraCost);
        return "redirect:/expedition/" + extraCost.getExpedition().getId() + "/addExtraCost";
    }
}
