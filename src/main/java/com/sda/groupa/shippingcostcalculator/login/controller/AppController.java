package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import com.sda.groupa.shippingcostcalculator.login.model.UserProvider;
import com.sda.groupa.shippingcostcalculator.login.workerStrategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AppController {

    private DriverStrategy driverStrategy;
    private ExpeditionService expeditionService;
    private UserProvider userProvider;

    public AppController(DriverStrategy driverStrategy, ExpeditionService expeditionService, UserProvider userProvider) {
        this.driverStrategy = driverStrategy;
        this.expeditionService = expeditionService;
        this.userProvider = userProvider;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public String getHomePage() {

        UserAuthority userAuthority = userProvider.getUser().getRole().getUserAuthority();
        String redirect;

        if(userAuthority.equals(UserAuthority.DRIVER)){
            redirect = "redirect:/DriverHome";
        }
        else if (userAuthority.equals(UserAuthority.SPEDYTOR)){
            redirect = "redirect:/SpedytorHome";
        } else {
            redirect = "redirect:/login";
        }

        return redirect;
    }

    @GetMapping("/DriverHome")
    public ModelAndView getUserHomePage(Principal principal) {

        ModelAndView modelAndView = driverStrategy.getDriverModelAndView();


        return modelAndView;
    }


    @GetMapping("/SpedytorHome")
    public ModelAndView getSpedytorHomePage(Principal principal) {

        ModelAndView modelAndView = new ModelAndView("spedytorHome");

        modelAndView.addObject("expeditions",expeditionService.getExpeditions());

        return modelAndView;
    }

}
