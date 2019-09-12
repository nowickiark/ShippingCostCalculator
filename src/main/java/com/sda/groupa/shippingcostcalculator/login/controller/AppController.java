package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@SessionAttributes("driver")
public class AppController {


    private UserDetailsService userDetailsService;
    private DriverService driverService;

    public AppController(UserDetailsService userDetailsService, DriverService driverService) {
        this.userDetailsService = userDetailsService;
        this.driverService = driverService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView getHomePage(Principal principal) {


        ModelAndView modelAndView = new ModelAndView("home");

        String userName = principal.getName();

        User user = userDetailsService.findUserByUsername(userName).orElseThrow(() -> new RuntimeException("Unavailable"));

        if (user.getRole().getAuthority().equals("DRIVER")){

            Driver driver  = driverService.findDriverByUser(userName).orElseThrow(() -> new RuntimeException("Unavailable"));

            modelAndView.addObject("driver",driver);
        }

        return modelAndView;
    }

}
