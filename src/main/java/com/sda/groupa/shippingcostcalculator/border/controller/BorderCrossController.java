package com.sda.groupa.shippingcostcalculator.border.controller;

import com.google.common.collect.Lists;
import com.sda.groupa.shippingcostcalculator.border.model.BorderCross;
import com.sda.groupa.shippingcostcalculator.border.model.Borders;
import com.sda.groupa.shippingcostcalculator.border.service.BorderCrossService;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.login.strategy.DriverStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class BorderCrossController {
    private final BorderCrossService borderCrossService;
    private final DriverStrategy driverStrategy;
    private final ExpeditionService expeditionService;

    public BorderCrossController(BorderCrossService borderCrossService, DriverStrategy driverStrategy, ExpeditionService expeditionService) {
        this.borderCrossService = borderCrossService;
        this.driverStrategy = driverStrategy;
        this.expeditionService = expeditionService;
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/{id}/addBorderCross")
    public String showAddPageOfBorderCrossByExpedition (Model model, @PathVariable("id") Long id ){
        Expedition expedition = expeditionService.getExpeditionById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        List<BorderCross> borderCrossList = Lists.reverse(borderCrossService.findListOfBorderCrossesByExpedition(expedition));
        List<Borders> bordersList = borderCrossService.getListOfBorders();
        BorderCross borderCross = new BorderCross();
        borderCross.setExpedition(expedition);
        model.addAttribute("borderCrossList",borderCrossList);
        model.addAttribute("bordersList",bordersList);
        model.addAttribute("newBorderCross",borderCross);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "expeditionExtras/borderCross-list-add";
    }

    //Thymeleaf
    @GetMapping(value = "/expedition/addBorderCross/{id}")
    public String showEditPageOfBorderCrossByExpedition (Model model, @PathVariable("id") Long id ){
        BorderCross borderCross = borderCrossService.findBorderCrossById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
        Expedition expedition = borderCross.getExpedition();
        List<BorderCross> borderCrossList = Lists.reverse(borderCrossService.findListOfBorderCrossesByExpedition(expedition));
        List<Borders> bordersList = borderCrossService.getListOfBorders();
        model.addAttribute("borderCrossList",borderCrossList);
        model.addAttribute("bordersList",bordersList);
        model.addAttribute("newBorderCross",borderCross);
        model.addAttribute("currencyCodeTypeList", CurrencyCode.values());
        return "expeditionExtras/borderCross-list-add";
    }

    //Thymeleaf
    @PostMapping(value = "/expedition/addBorderCross")
    public String addBorderCross(@ModelAttribute BorderCross borderCross){
        borderCrossService.addBorderCrossing(borderCross);
        return "redirect:/expedition/" + borderCross.getExpedition().getId() + "/addBorderCross";
    }

    //Thymeleaf
    @GetMapping(value = "/border/add")
    public String showViewAddBorder(Model model){
        List<Borders> bordersList = Lists.reverse(borderCrossService.getListOfBorders());
        model.addAttribute("newBorder",new Borders());
        model.addAttribute("bordersList",bordersList);
        return "expeditionExtras/border-add";
     }

     @GetMapping(value = "/border/add/{id}")
     public String showViewEditBorder(@PathVariable long id, Model model){
         Borders borders = borderCrossService.findBorderById(id).orElseThrow(() -> new RuntimeException("Unavailable"));
         List<Borders> bordersList = Lists.reverse(borderCrossService.getListOfBorders());
         model.addAttribute("newBorder",borders);
         model.addAttribute("bordersList",bordersList);
         return "expeditionExtras/border-add";
     }

     //ThymeLeaf
     @PostMapping(value = "/border/add")
    public String addNewBorder(@ModelAttribute Borders borders){
        borderCrossService.addBorder(borders);
        return "redirect:/border/add";
     }

/*    @PostMapping("/borderCross/add")
    public String addBorderCrossing(@ModelAttribute BorderCross borderCross) {
        Driver driver = driverStrategy.getDriver();
        borderCross.setExpedition(driver.getExpedition());
        borderCrossService.addBorderCrossing(borderCross);
        return "redirect:/listOfBorderCrosses";
    }

    @PostMapping("/borders/add")
    public String addBorder(@ModelAttribute Borders borders) {
        borderCrossService.addBorder(borders);
        return "redirect:/listOfBorders";
    }*/

/*    @GetMapping("/borderCross/add")
    public ModelAndView getBorderCrossForm() {
        ModelAndView modelAndView = new ModelAndView("borderCross");
        BorderCross borderCross = new BorderCross();
        List<Borders> borders = borderCrossService.getListOfBorders();
        modelAndView.addObject("borderCross", borderCross);
        modelAndView.addObject("listOfBorders", borders);
        return modelAndView;
    }

    @GetMapping("/borders/add")
    public ModelAndView getBorderForm() {
        ModelAndView modelAndView = new ModelAndView("borders");
        Borders borders = new Borders();
        modelAndView.addObject("borders", borders);
        return modelAndView;
    }

    @GetMapping("/borderCross/add/{id}")
    public ModelAndView editBorderCross(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("borderCross");
        BorderCross borderCross = borderCrossService.findBorderCrossById(id).orElseThrow(() -> new BorderCrossNotFoundException("Unavailable"));
        modelAndView.addObject(borderCross);
        return modelAndView;
    }

    @GetMapping("/borders/add/{id}")
    public ModelAndView editBorder(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("borders");
        modelAndView.addObject("borders", borderCrossService.findBorderById(id));
        return modelAndView;
    }

    @GetMapping("/listOfBorderCrosses")
    public ModelAndView getListOfBorderCrosses() {
        ModelAndView modelAndView = new ModelAndView("listOfBorderCrosses");
        List<BorderCross> listOfBorderCrosses = borderCrossService.getListOfBorderCrosses();
        modelAndView.addObject("listOfBorderCrosses", listOfBorderCrosses);
        return modelAndView;
    }

    @GetMapping("/expedition/listOfBorderCrosses")
    public ModelAndView getListOfBorderCrossesByExpedition() {
        ModelAndView modelAndView = new ModelAndView("listOfBorderCrosses");
        Driver driver = driverStrategy.getDriver();
        List<BorderCross> listOfBorderCrosses = borderCrossService.findListOfBorderCrossesByExpedition(driver.getExpedition());
        modelAndView.addObject("listOfBorderCrosses", listOfBorderCrosses);
        return modelAndView;
    }

    @GetMapping("/listOfBorders")
    public ModelAndView getListOfBorders() {
        ModelAndView modelAndView = new ModelAndView("listOfBorders");
        List<Borders> listOfBorders = borderCrossService.getListOfBorders();
        modelAndView.addObject("listOfBorders", listOfBorders);
        return modelAndView;
    }*/




}
