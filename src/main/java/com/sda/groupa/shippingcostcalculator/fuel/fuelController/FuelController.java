package com.sda.groupa.shippingcostcalculator.fuel.fuelController;


import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuel.fuelService.FuelService;
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

@Controller
public class FuelController {

    private final FuelService fuelService;
    private final DriverStrategy driverStrategy;
    private final CurrencyRateService currencyRateService;
    private final ExpeditionService expeditionService;

    public FuelController(FuelService fuelService, DriverStrategy driverStrategy, CurrencyRateService currencyRateService, ExpeditionService expeditionService){
        this.fuelService=fuelService;
        this.driverStrategy = driverStrategy;
        this.currencyRateService = currencyRateService;
        this.expeditionService = expeditionService;
    }

    //Thymeleaf - Asia
    @GetMapping(value = "/driver/listOfFuels")
    public String getFuelsByExpeditions(Model model){
        Driver driver = driverStrategy.getDriver();
        List<Fuel> listOfFuelings = fuelService.findFuelsByExpedition(driver.getExpedition());
        model.addAttribute("listOfFuelings", listOfFuelings);
        return "fuelList-DriverView";
    }

    //Thymeleaf - Asia
    @PostMapping(value = "/driver/addFuel")
    public String addFueling(@ModelAttribute Fuel fuel, Model model) throws IOException {
        Driver driver = driverStrategy.getDriver();
        fuel.setExpedition(driver.getExpedition());
        model.addAttribute("newFuel", new Fuel());
        fuelService.addFueling(fuel);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(fuel.getCurrencyCode(),fuel.getDateOfFueling());
        return "fuelAdd-DriverView";
    }
    //Thymeleaf - Asia
    @GetMapping(value = "/driver/addFuel")
    public String addFuelingPage (Model model){
        model.addAttribute("newFuel", new Fuel());
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values()); //for drop-down list in the view
        return "fuelAdd-DriverView";
    }

    //Thymleaf - Asia
    @GetMapping(value = "/driver/updatefuel/{fuelId}")
    public String updateFuelingPage (Model model, @PathVariable("fuelId") Long fuelId){
        Fuel fuel = fuelService.findById(fuelId).orElseThrow(()-> new RuntimeException("Unavailable"));
        model.addAttribute("fuel", fuelService.findById(fuelId).orElseThrow(()-> new RuntimeException("Unavailable")));
        model.addAttribute("newFuel",fuel);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "fuelAdd-DriverView";
    }

    //Thymeleaf - Arek
    @PostMapping(value = "/expedition/addFuel")
    public String addFuelToTheExpedition(@ModelAttribute Fuel fuel){
        fuelService.addFueling(fuel);
        return "redirect:/expedition/" + fuel.getExpedition().getId() + "/addFuel";
    }

    //Thymeleaf - Arek
    @GetMapping(value = "/expedition/addFuel/{fuelId}")
    public String showPageToEditExistingFuel(Model model,@PathVariable("fuelId") Long fuelId){
        Fuel fuel = fuelService.findById(fuelId).orElseThrow(()-> new RuntimeException("Unavailable"));
        Expedition expedition = fuel.getExpedition();
        List<Fuel> fuelList = Lists.reverse(fuelService.findFuelsByExpedition(expedition));
        model.addAttribute("fuelList",fuelList);
        model.addAttribute("newFuel",fuel);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "fuel-list-add";
    }

    //Thymeleaf - Arek
    @GetMapping(value = "/expedition/{id}/addFuel")
    public String showPageOfFuelingsByExpedition (Model model,@PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<Fuel> fuelList = Lists.reverse(fuelService.findFuelsByExpedition(expedition));
        model.addAttribute("fuelList",fuelList);
        Fuel fuel = new Fuel();
        fuel.setExpedition(expedition);
        model.addAttribute("newFuel",fuel);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "fuel-list-add";
    }

}
