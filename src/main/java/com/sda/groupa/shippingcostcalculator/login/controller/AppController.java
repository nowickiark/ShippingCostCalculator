package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.service.SteerRole;
import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import com.sda.groupa.shippingcostcalculator.login.workerStrategy.WorkerStrategy;
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

    private SteerRole steerRole;

    public AppController(SteerRole steerRole) {
        this.steerRole = steerRole;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView getHomePage(Principal principal, HttpServletRequest request) {

        ModelAndView modelAndView = steerRole.runStrategy(principal.getName());

        return modelAndView;
    }

}
