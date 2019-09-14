package com.sda.groupa.shippingcostcalculator.login.workerStrategy;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Scope(value = "session",  proxyMode = ScopedProxyMode.INTERFACES)
public class DriverStrategy implements WorkerStrategy {

    private DriverService driverService;
    private ExpeditionService expeditionService;
    private User user;
    private Driver driver;

    public DriverStrategy(DriverService driverService, ExpeditionService expeditionService) {
        this.driverService = driverService;
        this.expeditionService = expeditionService;
    }

    @Override
    public ModelAndView setUpSession(User user) {
        ModelAndView modelAndView = new ModelAndView("home");
        this.user = user;
        driver  = driverService.findDriverByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("Unavailable"));
        driver.setExpedition(expeditionService.getExpeditionById(1L).get());
        driverService.addDriver(driver);
        return modelAndView;
    }

    @Override
    public UserAuthority getUserAuthority() {
        return UserAuthority.DRIVER;
    }

    public User getUser() {
        return user;
    }

}
