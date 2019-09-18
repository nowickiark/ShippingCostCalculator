package com.sda.groupa.shippingcostcalculator.fuel.fuelController;


import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuel.fuelService.FuelService;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class FuelController {

    private final FuelService fuelService;
    private final DriverStrategy driverStrategy;
    private final CurrencyRateService currencyRateService;

    public FuelController(FuelService fuelService, DriverStrategy driverStrategy, CurrencyRateService currencyRateService){
        this.fuelService=fuelService;
        this.driverStrategy = driverStrategy;
        this.currencyRateService = currencyRateService;
    }

    @GetMapping(value = "/fuelings")
    public ModelAndView getFuelPageWithListOfFuelings(){
        List<Fuel> listOfFuelings = fuelService.findAll();
        ModelAndView modelAndView = new ModelAndView("fuel");
        modelAndView.addObject("listOfFuelings", listOfFuelings);
        return modelAndView;
    }

    @GetMapping(value = "/expedition/listOfFuels")
    public ModelAndView getFuelsByExpeditions(){
        ModelAndView modelAndView = new ModelAndView("fuel");
        Driver driver = driverStrategy.getDriver();
        List<Fuel> listOfFuelings = fuelService.findFuelsByExpedition(driver.getExpedition());
        modelAndView.addObject("listOfFuelings", listOfFuelings);
        return modelAndView;
    }

    @PostMapping(value = "/addfuel")    //fuel
    public String addFueling(@ModelAttribute Fuel fuel) throws IOException {
        Driver driver = driverStrategy.getDriver();
        fuel.setExpedition(driver.getExpedition());
        fuelService.addFueling(fuel);
        //=====check if currency rate for given code and date is already present in repository, if not then take it from API and add to repository=======
        currencyRateService.checkLatestCurrencyExchangeRate(fuel.getCurrencyCode(),fuel.getDateOfFueling());
        return "redirect:/fuelings";
    }

    @GetMapping(value = "/addfuel")   //fuel/add
    public ModelAndView addFuelingPage (){
        ModelAndView modelAndView = new ModelAndView("addFuel");
        modelAndView.addObject("fuel", new Fuel());
        modelAndView.addObject("currencyCodeType", CurrencyCode.values()); //for drop-down list in the view
        return modelAndView;
    }

    @PostMapping(value = "/updatefuel")
    public String updateFueling(@ModelAttribute Fuel fuel){
        Driver driver = driverStrategy.getDriver();
        fuel.setExpedition(driver.getExpedition());
        fuelService.updateFueling(fuel);
        return "redirect:/fuelings";
    }

    @GetMapping(value = "/updatefuel/{id}")
    public ModelAndView updateFuelingPage (@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("addFuel");
        modelAndView.addObject("fuel", fuelService.findById(id));
        modelAndView.addObject("update", true);
        return modelAndView;
    }
}
