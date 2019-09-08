package com.sda.groupa.shippingcostcalculator.driverController;

import com.sda.groupa.shippingcostcalculator.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driverService.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping(value = "/drivers")
    public ModelAndView getDrivers() {
        List<Driver> listOfDrivers = driverService.findAll();
        ModelAndView modelAndView = new ModelAndView("driver");
        modelAndView.addObject("listOfDrivers", listOfDrivers);
        return modelAndView;
    }

    @PostMapping(value = "/driver/add")
    public String addDriver(@ModelAttribute Driver driver) {
        driverService.addDriver(driver);
        return "redirect:/drivers";
    }

    @GetMapping(value = "/driver/add")
    public ModelAndView addDriverPage() {
        ModelAndView modelAndView = new ModelAndView("addDriver");
        modelAndView.addObject("driver", new Driver());
        return modelAndView;
    }

    @PostMapping(value = "/driver/update")
    public String updateDriver(@ModelAttribute Driver driver) {
        driverService.updateDriver(driver);
        return "redirect:/drivers";
    }

    @GetMapping(value = "/driver/update/{id}")
    public ModelAndView updateDriverPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("addDriver");
        modelAndView.addObject("driver", driverService.findById(id));
        modelAndView.addObject("update", true);
        return modelAndView;
    }

    @GetMapping(value = "/driverMainMenu")
    public ModelAndView getDriversMainMenuPage() {
        ModelAndView modelAndView = new ModelAndView("driversMainMenu");
        return modelAndView;
    }
}



