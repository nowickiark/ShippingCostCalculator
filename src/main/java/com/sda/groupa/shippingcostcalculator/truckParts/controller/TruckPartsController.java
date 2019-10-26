package com.sda.groupa.shippingcostcalculator.truckParts.controller;

import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import com.sda.groupa.shippingcostcalculator.truckParts.model.TruckParts;
import com.sda.groupa.shippingcostcalculator.truckParts.service.TruckPartsService;
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
public class TruckPartsController {
    private final TruckPartsService truckPartsService;
    private final DriverStrategy driverStrategy;
    private final CurrencyRateService currencyRateService;
    private final ExpeditionService expeditionService;

    public TruckPartsController(TruckPartsService truckPartsService, DriverStrategy driverStrategy, CurrencyRateService currencyRateService, ExpeditionService expeditionService) {
        this.truckPartsService = truckPartsService;
        this.driverStrategy = driverStrategy;
        this.currencyRateService = currencyRateService;
        this.expeditionService = expeditionService;
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
        List<TruckParts> truckParts = truckPartsService.getTruckPartsByExpedition(driver.getExpedition());
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
    public String truckParts (@ModelAttribute TruckParts truckParts) throws IOException {
        Driver driver = driverStrategy.getDriver();
        truckParts.setExpedition(driver.getExpedition());
        truckPartsService.addTruckParts(truckParts);
        //==check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(truckParts.getCurrencyCode(),truckParts.getDateOfPurchase());
        return "redirect:/expedition/listOfTruckParts";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/{id}/addTruckPart")
    public String showAddPageOfTruckPartsByExpedition (Model model, @PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<TruckParts> truckPartsList = Lists.reverse(truckPartsService.getTruckPartsByExpedition(expedition));
        TruckParts truckPart = new TruckParts();
        truckPart.setExpedition(expedition);
        model.addAttribute("truckPartsList",truckPartsList);
        model.addAttribute("newTruckPart",truckPart);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "truckPart-list-add";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/addTruckPart/{id}")
    public String showEditPageOfTruckPartsByExpedition (Model model, @PathVariable("id") Long id ){
        TruckParts truckPart = truckPartsService.getById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        Expedition expedition = truckPart.getExpedition();
        List<TruckParts> truckPartsList = Lists.reverse(truckPartsService.getTruckPartsByExpedition(expedition));
        model.addAttribute("truckPartsList",truckPartsList);
        model.addAttribute("newTruckPart",truckPart);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "truckPart-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addTruckPart")
    public String addExtraCostToExpedition(@ModelAttribute TruckParts truckPart){
        truckPartsService.addTruckParts(truckPart);
        return "redirect:/expedition/" + truckPart.getExpedition().getId() + "/addTruckPart";
    }


}




