package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.workerStrategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AppController {

    private DriverStrategy driverStrategy;
    private ExpeditionService expeditionService;

    public AppController(DriverStrategy driverStrategy, ExpeditionService expeditionService) {
        this.driverStrategy = driverStrategy;
        this.expeditionService = expeditionService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView getHomePage(Principal principal) {

        ModelAndView modelAndView = driverStrategy.getDriverModelAndView();

        driverStrategy.setUpSession(principal.getName());

        return modelAndView;
    }

    @GetMapping("/SpedytorHome")
    public ModelAndView getSpedytorHomePage(Principal principal) {

        ModelAndView modelAndView = new ModelAndView("spedytorHome");

        modelAndView.addObject("expeditions",expeditionService.getExpeditions());

        return modelAndView;
    }

}
