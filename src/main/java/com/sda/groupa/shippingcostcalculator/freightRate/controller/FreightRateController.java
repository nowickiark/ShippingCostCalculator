package com.sda.groupa.shippingcostcalculator.freightRate.controller;

import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.freightRate.exception.FreightNotFoundException;
import com.sda.groupa.shippingcostcalculator.freightRate.model.FreightRate;
import com.sda.groupa.shippingcostcalculator.freightRate.service.FreightRateService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
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

@Controller
public class FreightRateController {

    private final FreightRateService freightRateService;
    private final ExpeditionService expeditionService;
    private final CurrencyRateService currencyRateService;

    public FreightRateController(FreightRateService freightRateService, ExpeditionService expeditionService, CurrencyRateService currencyRateService) {
        this.freightRateService = freightRateService;
        this.expeditionService = expeditionService;
        this.currencyRateService = currencyRateService;
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/{id}/addFreightRate")
    public String showPageOfFreightRatesByExpedition (Model model, @PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<FreightRate> freightRatesList = Lists.reverse(freightRateService.findFreightRatesByExpedition(expedition));
        model.addAttribute("freightRatesList",freightRatesList);
        Fuel fuel = new Fuel();
        FreightRate freightRate = new FreightRate();
        freightRate.setExpedition(expedition);
        model.addAttribute("newFreightRate",freightRate);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "expeditionExtras/freightRate-list-add";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/addFreightRate/{freightRateId}")
    public String showPageToEditExistingFreightRate(Model model,@PathVariable("freightRateId") Long freightRateId){
        FreightRate freightRate = freightRateService.getFreightrateById(freightRateId).orElseThrow(()-> new RuntimeException("Unavailable"));
        Expedition expedition = freightRate.getExpedition();
        List<FreightRate> freightRatesList = Lists.reverse(freightRateService.findFreightRatesByExpedition(expedition));
        model.addAttribute("freightRatesList",freightRatesList);
        model.addAttribute("newFreightRate",freightRate);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "expeditionExtras/freightRate-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addFreightRate")
    public String addFuelToTheExpedition(@ModelAttribute FreightRate freightRate){
        freightRateService.saveFreightRate(freightRate);
        return "redirect:/expedition/" + freightRate.getExpedition().getId() + "/addFreightRate";
    }

 /*   @GetMapping("/freightRateList")
    public ModelAndView getFrightRateList(){
        ModelAndView modelAndView = new ModelAndView("freightRateList");
        modelAndView.addObject("freightRates",freightRateService.getAllFreightRates());
        return modelAndView;
    }

    @GetMapping("/expedition/freightRateList/{expeditionId}")
    public ModelAndView getFreightRateListByExpedition(@PathVariable Long expeditionId){
        ModelAndView modelAndView = new ModelAndView("freightRateList");
        Expedition expedition = expeditionService.getExpeditionById(expeditionId).orElseThrow(() -> new RuntimeException("Unavailable"));
        modelAndView.addObject("freightRates",freightRateService.findFreightRatesByExpedition(expedition));
        return modelAndView;
    }

    @GetMapping("/freightRate/add")
    public ModelAndView showFreightRateForm(){
        FreightRate freightRate = new FreightRate();
        return getAddingView(freightRate);
    }

    @GetMapping("/freightRate/add/{idExpedition}")
    public ModelAndView showFreightRateFormWithExpedition(@PathVariable Long idExpedition){
        FreightRate freightRate = new FreightRate();
        freightRate.setExpedition(expeditionService.getExpeditionById(idExpedition).orElseThrow(() -> new RuntimeException("Unavailable")));
        return getAddingView(freightRate);
    }

    private ModelAndView getAddingView(FreightRate freightRate){
        ModelAndView modelAndView = new ModelAndView("addFreightRate");
        List<Expedition> expeditions = expeditionService.findCurrentExpeditions();
        modelAndView.addObject("freightRate",freightRate);
        modelAndView.addObject("expeditions",expeditions);
        modelAndView.addObject("currencyCodeType", CurrencyCode.values());
        return modelAndView;
    }

    @GetMapping("/freightRate/edit/{id}")
    public ModelAndView editFreightRate(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("addFreightRate");
        FreightRate freightRate = freightRateService.getFreightrateById(id).orElseThrow(() -> new FreightNotFoundException("Unavailable"));
        List<Expedition> expeditions = expeditionService.findCurrentExpeditions();
        modelAndView.addObject("freightRate",freightRate);
        modelAndView.addObject("expeditions",expeditions);
        modelAndView.addObject("currencyCodeType", CurrencyCode.values());
        return modelAndView;
    }

    @PostMapping("/freightRate/add")
    public String addFreightrate(@ModelAttribute FreightRate freightRate) throws IOException {
        currencyRateService.checkLatestCurrencyExchangeRate(freightRate.getCurrencyCode(),freightRate.getDate());
        freightRateService.saveFreightRate(freightRate);
        return "redirect:/spedytorHome";
    }

    @GetMapping("/expedition/freightRateSum/{expeditionId}")
    public String getSumOfFreightRates(@PathVariable Long expeditionId ){
        BigDecimal sum = freightRateService.sumOfFreightRatesForExpedition(expeditionId);
        System.out.println("############################# sum of all freight rates " + sum + "################################################################################");

        return "redirect:/spedytorHome";
    }*/



}
