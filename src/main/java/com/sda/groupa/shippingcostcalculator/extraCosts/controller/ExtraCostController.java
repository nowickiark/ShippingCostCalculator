package com.sda.groupa.shippingcostcalculator.extraCosts.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class ExtraCostController {

    private final ExtraCostService extraCostService;
    private final DriverStrategy driverStrategy;
    private final CurrencyRateService currencyRateService;



    public ExtraCostController(ExtraCostService extraCostService, DriverStrategy driverStrategy, DriverService driverService, CurrencyRateService currencyRateService, ExpeditionService expeditionService) {
        this.extraCostService = extraCostService;
        this.driverStrategy = driverStrategy;
        this.currencyRateService = currencyRateService;
    }

    //Thymeleaf - Asia
    @GetMapping(value = "/driver/extracosts/add")
    public String getPageForAddingExtraCost(Model model) {
        model.addAttribute("newExtraCost", new ExtraCost());
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values()); //for drop-down list in the view
        return "extraCostsAdd-DriverView";
    }

    //Thymeleaf - Asia
    @PostMapping(value = "/driver/extracosts/add")
    public String addExtraCost(@ModelAttribute ExtraCost extraCost, Model model) throws IOException {
        Driver driver = driverStrategy.getDriver();
        extraCost.setExpedition(driver.getExpedition());
        model.addAttribute("newExtraCost", new ExtraCost());
        extraCostService.addExtraCost(extraCost);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(extraCost.getCurrencyCode(),extraCost.getDateOfPurchase());
        return "extraCostsAdd-DriverView";
    }
    //Thymleaf - Asia
    @GetMapping(value = "/driver/updateExtraCost/{extraCostId}")
    public String updateExtraCostPage (Model model, @PathVariable("extraCostId") Long extraCostId){
        ExtraCost extraCost = extraCostService.getById(extraCostId).orElseThrow(()-> new RuntimeException("Unavailable"));
        model.addAttribute("extraCost", extraCostService.getById(extraCostId).orElseThrow(()-> new RuntimeException("Unavailable")));
        model.addAttribute("newExtraCost", extraCost);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "extraCostsAdd-DriverView";
    }

    //Thymeleaf - Asia
    @GetMapping(value = "/driver/listOfExtraCosts")
    public String getExtraCosts(Model model){
        Driver driver = driverStrategy.getDriver();
        List<ExtraCost> extraCosts = extraCostService.getExtraCostsByExpetion(driver.getExpedition());
        model.addAttribute("listOfExtraCosts",extraCosts);
        return "extraCostsList-DriverView";

    }


    //Thymeleaf - Arek
    @PostMapping("/extracost/add")
    public String extraCosts (@ModelAttribute ExtraCost extracost) throws IOException {
        Expedition expedition = driverStrategy.getExpedition();
        extracost.setExpedition(expedition);
        extraCostService.addExtraCost(extracost);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(extracost.getCurrencyCode(),extracost.getDateOfPurchase());
        return "redirect:/expedition/listOfExtraCosts";
    }
}
