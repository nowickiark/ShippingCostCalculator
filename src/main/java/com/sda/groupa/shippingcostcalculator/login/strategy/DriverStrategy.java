package com.sda.groupa.shippingcostcalculator.login.strategy;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.exceptions.UserNotFoundException;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.model.UserProvider;
import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DriverStrategy {

    private DriverService driverService;
    private ExpeditionService expeditionService;
    private UserDetailsService userDetailsService;
    private UserProvider userProvider;

    public DriverStrategy(DriverService driverService, ExpeditionService expeditionService, UserDetailsService userDetailsService) {
        this.driverService = driverService;
        this.expeditionService = expeditionService;
        this.userDetailsService = userDetailsService;
    }

    public ModelAndView getDriverModelAndView(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    public User getUser() {
        return userProvider.getUser();
    }

    public Driver getDriver(){
        return driverService.findDriverByUsername(getUser().getUsername()).orElseThrow(() -> new UserNotFoundException("Unavailable"));
    }

    public Expedition getExpedition(){
        return getDriver().getExpedition();
    }


}
