package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class AppController {

    private UserDetailsService userDetailsService;
    private DriverService driverService;
    private ExpeditionService expeditionService;

    public AppController(UserDetailsService userDetailsService, DriverService driverService, ExpeditionService expeditionService) {
        this.userDetailsService = userDetailsService;
        this.driverService = driverService;
        this.expeditionService = expeditionService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView getHomePage(Principal principal, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("home");

        String userName = principal.getName();

        User user = userDetailsService.findUserByUsername(userName).orElseThrow(() -> new RuntimeException("Unavailable"));

        if (user.getRole().getAuthority().equals("DRIVER")){

            Driver driver  = driverService.findDriverByUsername(userName).orElseThrow(() -> new RuntimeException("Unavailable"));

            driver.setExpedition(expeditionService.getExpeditionById(1L).get());

            request.getSession().setAttribute("driver",driver);
        }

        return modelAndView;
    }

}
