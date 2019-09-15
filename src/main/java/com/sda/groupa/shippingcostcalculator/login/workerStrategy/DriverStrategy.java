package com.sda.groupa.shippingcostcalculator.login.workerStrategy;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DriverStrategy {

    private DriverService driverService;
    private ExpeditionService expeditionService;
    private UserDetailsService userDetailsService;
    private User user;
    private Driver driver;
    private Expedition expedition;

    public DriverStrategy(DriverService driverService, ExpeditionService expeditionService, UserDetailsService userDetailsService) {
        this.driverService = driverService;
        this.expeditionService = expeditionService;
        this.userDetailsService = userDetailsService;
    }

    public void setUpSession(String username) {

        User user = userDetailsService.findUserByUsername(username).orElseThrow(()->new RuntimeException("Unavailable"));
        driver  = driverService.findDriverByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("Unavailable"));
        driver.setExpedition(expeditionService.getExpeditionById(1L).get());
        driverService.addDriver(driver);
        expedition = driver.getExpedition();
    }

    public ModelAndView getDriverModelAndView(){
        ModelAndView modelAndView = new ModelAndView("home");

        return modelAndView;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }
}
