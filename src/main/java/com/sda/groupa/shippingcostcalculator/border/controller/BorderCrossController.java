package com.sda.groupa.shippingcostcalculator.border.controller;

import com.sda.groupa.shippingcostcalculator.border.model.BorderCross;
import com.sda.groupa.shippingcostcalculator.border.model.Borders;
import com.sda.groupa.shippingcostcalculator.border.service.BorderCrossService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BorderCrossController {
    private BorderCrossService borderCrossService;

    public BorderCrossController(BorderCrossService borderCrossService) {
        this.borderCrossService = borderCrossService;
    }

    @GetMapping("/borderCross/add")
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
public ModelAndView editBorderCross(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("borderCross");

    BorderCross borderCross = borderCrossService.findBorderCrossById(id).orElseThrow(() -> new RuntimeException("Unavailable"));

    modelAndView.addObject(borderCross);

    return modelAndView;


}

    @GetMapping("/borders/add/{id}")
    public ModelAndView editBorder(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("borders");

//        List<Borders> listOfBorders = borderCrossService.getListOfBorders();

        //Borders borders = borderCrossService.findBorderById(id).orElseThrow(() -> new RuntimeException("Unavailable"));

        modelAndView.addObject("borders", borderCrossService.findBorderById(id));


        return modelAndView;

    }

    @GetMapping("/listOfBorderCrosses")
    public ModelAndView getListOfBorderCrosses(){
        ModelAndView modelAndView = new ModelAndView("listOfBorderCrosses");

        List<BorderCross> listOfBorderCrosses = borderCrossService.getListOfBorderCrosses();

        modelAndView.addObject("listOfBorderCrosses",listOfBorderCrosses);
        return modelAndView;
    }

    @GetMapping("/listOfBorders")
    public ModelAndView getListOfBorders(){
        ModelAndView modelAndView = new ModelAndView("listOfBorders");

        List<Borders> listOfBorders = borderCrossService.getListOfBorders();

        modelAndView.addObject("listOfBorders",listOfBorders);
        return modelAndView;
    }

    @PostMapping("/borderCross/add")
    public String addBorderCrossing(@ModelAttribute BorderCross borderCross) {
        borderCrossService.addBorderCrossing(borderCross);
        return "redirect:/listOfBorderCrosses";
    }

    @PostMapping("/borders/add")
    public String addBorder(@ModelAttribute Borders borders) {
        borderCrossService.addBorder(borders);
        return "redirect:/listOfBorders";
    }

}