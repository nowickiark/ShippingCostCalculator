package com.sda.groupa.shippingcostcalculator.extraCosts.controller;

import com.google.common.collect.Lists;
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
import java.io.IOException;
import java.util.List;


@Controller
public class ExtraCostController {

    private final ExtraCostService extraCostService;
    private final DriverStrategy driverStrategy;
    private final CurrencyRateService currencyRateService;
    private final ExpeditionService expeditionService;


    public ExtraCostController(ExtraCostService extraCostService, DriverStrategy driverStrategy, DriverService driverService, CurrencyRateService currencyRateService, ExpeditionService expeditionService) {
        this.extraCostService = extraCostService;
        this.driverStrategy = driverStrategy;
        this.currencyRateService = currencyRateService;
        this.expeditionService = expeditionService;
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
        return "expeditionExtras/extraCost-list-add";
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
        return "expeditionExtras/extraCost-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addExtraCost")
    public String addExtraCostToExpedition(@ModelAttribute ExtraCost extraCost) throws IOException {
        extraCostService.addExtraCost(extraCost);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(extraCost.getCurrencyCode(),extraCost.getDateOfPurchase());
        return "redirect:/expedition/" + extraCost.getExpedition().getId() + "/addExtraCost";
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
        return "redirect:/driver/extracosts/add";
    }
    //Thymleaf - Asia
    @GetMapping(value = "/driver/updateExtraCost/{extraCostId}")
    public String updateExtraCostPage (Model model, @PathVariable("extraCostId") Long extraCostId){
        ExtraCost extraCost = extraCostService.getById(extraCostId).orElseThrow(()-> new RuntimeException("Unavailable"));
        model.addAttribute("extraCost", extraCostService.getById(extraCostId).orElseThrow(()-> new RuntimeException("Unavailable")));
        model.addAttribute("newExtraCost", extraCost);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        //aa
        model.addAttribute("action", "Update");
        return "extraCostsAdd-DriverView";
    }

    //Thymleaf - Asiaaaa
    @PostMapping(value = "/driver/updateExtraCost/{extraCostId}")
    public String goBackToExtraCostPageAfterUpdate (Model model, @ModelAttribute ExtraCost extraCost) throws IOException {
        Driver driver = driverStrategy.getDriver();
        extraCost.setExpedition(driver.getExpedition());
        model.addAttribute("newExtraCost", extraCost);
        extraCostService.addExtraCost(extraCost);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(extraCost.getCurrencyCode(),extraCost.getDateOfPurchase());
        return "redirect:/driver/listOfExtraCosts";
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
