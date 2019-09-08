package com.sda.groupa.shippingcostcalculator.fuelController;

import com.sda.groupa.shippingcostcalculator.fuelModel.Fuel;
import com.sda.groupa.shippingcostcalculator.fuelService.FuelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FuelController {

    private final FuelService fuelService;

    public FuelController (FuelService fuelService){
        this.fuelService=fuelService;
    }


//    public ModelAndView getFuelPageWithListOfFuelings(@PathVariable Long id){
    @GetMapping(value = "/fuelings")
    public ModelAndView getFuelPageWithListOfFuelings(){
        List<Fuel> listOfFuelings = fuelService.findAll();
//        Expedition expedition = new Expedition();
//        Fuel fuel = new Fuel();
//        expedition.setId(id);
//        fuel.setExpedition(expedition);
        ModelAndView modelAndView = new ModelAndView("fuel");
        modelAndView.addObject("listOfFuelings", listOfFuelings);
        return modelAndView;
    }

    @PostMapping(value = "/addfuel")    //fuel
    public String addFueling(@ModelAttribute Fuel fuel){
        fuelService.addFueling(fuel);
        return "redirect:/fuelings";
    }

    @GetMapping(value = "/addfuel")   //fuel/add
    public ModelAndView addFuelingPage (){
        ModelAndView modelAndView = new ModelAndView("addFuel");
        modelAndView.addObject("fuel", new Fuel());
        return modelAndView;
    }

    @PostMapping(value = "/updatefuel")
    public String updateFueling(@ModelAttribute Fuel fuel){
        fuelService.updateFueling(fuel);
        return "redirect:/fuelings/" + fuel.getId();

    }
    @GetMapping(value = "/updatefuel/{id}")
    public ModelAndView updateFuelingPage (@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("addFuel");
        modelAndView.addObject("fuel", fuelService.findById(id));
        modelAndView.addObject("update", true);
        return modelAndView;
    }

}
