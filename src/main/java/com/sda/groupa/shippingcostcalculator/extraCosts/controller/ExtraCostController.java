package com.sda.groupa.shippingcostcalculator.extraCosts.controller;

import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ExtraCostController {
    private final ExtraCostService extraCostService;

    public ExtraCostController(ExtraCostService extraCostService) {
        this.extraCostService = extraCostService;
    }

    @GetMapping("/extracost/add")
    public ModelAndView getFormPage() {

        ExtraCost extraCost = new ExtraCost();
        ModelAndView modelAndView = new ModelAndView("extracost");
        modelAndView.addObject("extracost", extraCost);
        return modelAndView;
    }

    @GetMapping("/extracosts/list")
    public ModelAndView getExtraCosts(){

        ModelAndView modelAndView = new ModelAndView("extracostslist");
        List<ExtraCost> extraCosts = extraCostService.getExtraCosts();
        modelAndView.addObject("extracosts",extraCosts);
        return modelAndView;

    }

    @GetMapping("/extracosts/{id}")
    public ModelAndView getExtraCostsForm(@PathVariable Long id) {

        Optional<ExtraCost> extraCost = extraCostService.getById(id);
        ModelAndView modelAndView = new ModelAndView("extracost");
        modelAndView.addObject("extracost", extraCost);
        return modelAndView;
    }

    @PostMapping("/extracost/add")
    public String extraCosts (@ModelAttribute ExtraCost extracost) {

        extraCostService.addExtraCost(extracost);
        return "redirect:/extracosts/list";
    }

}
