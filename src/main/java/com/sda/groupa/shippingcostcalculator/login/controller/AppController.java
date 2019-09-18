package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import com.sda.groupa.shippingcostcalculator.login.model.UserProvider;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import com.sda.groupa.shippingcostcalculator.login.strategy.LoggingSwitch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AppController {

    private DriverStrategy driverStrategy;
    private ExpeditionService expeditionService;
    private UserProvider userProvider;
    private LoggingSwitch loggingSwitch;

    public AppController(DriverStrategy driverStrategy, ExpeditionService expeditionService, UserProvider userProvider, LoggingSwitch loggingSwitch) {
        this.driverStrategy = driverStrategy;
        this.expeditionService = expeditionService;
        this.userProvider = userProvider;
        this.loggingSwitch = loggingSwitch;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public String getHomePage() {
        UserAuthority userAuthority = userProvider.getUser().getRole().getUserAuthority();
        String redirect;
        redirect = "redirect:/" + loggingSwitch.getLogindView(userAuthority);
        return redirect;
    }

    @GetMapping("/driverHome")
    public ModelAndView getUserHomePage(Principal principal) {
        ModelAndView modelAndView = driverStrategy.getDriverModelAndView();
        return modelAndView;
    }

    @GetMapping("/spedytorHome")
    public ModelAndView getSpedytorHomePage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("spedytorHome");
        modelAndView.addObject("expeditions",expeditionService.getExpeditions());
        return modelAndView;
    }

}
