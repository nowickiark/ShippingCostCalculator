package com.sda.groupa.shippingcostcalculator.login.controller;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import com.sda.groupa.shippingcostcalculator.login.model.UserProvider;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import com.sda.groupa.shippingcostcalculator.login.strategy.LoggingSwitch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

    private final DriverStrategy driverStrategy;
    private final ExpeditionService expeditionService;
    private final UserProvider userProvider;
    private final LoggingSwitch loggingSwitch;
    private final DriverService driverService;


    public AppController(DriverStrategy driverStrategy, ExpeditionService expeditionService, UserProvider userProvider, LoggingSwitch loggingSwitch, DriverService driverService) {
        this.driverStrategy = driverStrategy;
        this.expeditionService = expeditionService;
        this.userProvider = userProvider;
        this.loggingSwitch = loggingSwitch;
        this.driverService = driverService;
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "page-login";
    }

    @GetMapping("/")
    public String getHomePage() {
        UserAuthority userAuthority = userProvider.getUser().getRole().getUserAuthority();
        String redirect;
        redirect = "redirect:/" + loggingSwitch.getLogindView(userAuthority);
        return redirect;
    }

/*    @GetMapping("/driverHome")
    public ModelAndView getUserHomePage() {
        ModelAndView modelAndView = driverStrategy.getDriverModelAndView();
        return modelAndView;
    }*/

    @GetMapping("/spedytorHome")
    public String getSpedytorHomePage(Model model) {
        model.addAttribute("expeditions",expeditionService.findOpenExpeditions());
        model.addAttribute("drivers",driverService.findAll());
        model.addAttribute("driver",new Driver());
        return "index";
    }

}
