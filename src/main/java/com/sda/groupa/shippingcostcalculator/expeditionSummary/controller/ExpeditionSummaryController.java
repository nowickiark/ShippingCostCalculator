package com.sda.groupa.shippingcostcalculator.expeditionSummary.controller;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.expedition.service.ExpeditionService;
import com.sda.groupa.shippingcostcalculator.expeditionSummary.model.ExpeditionSummary;
import com.sda.groupa.shippingcostcalculator.expeditionSummary.service.ExpeditionSummaryService;
import com.sda.groupa.shippingcostcalculator.extraCosts.service.ExtraCostService;
import com.sda.groupa.shippingcostcalculator.fuel.fuelService.FuelService;
import com.sda.groupa.shippingcostcalculator.truckParts.service.TruckPartsService;
import org.aspectj.weaver.ast.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.math.BigDecimal;
import java.util.Optional;
@Controller
public class ExpeditionSummaryController {
    private final ExpeditionSummaryService expeditionSummaryService;
    private final FuelService fuelService;
    private final ExtraCostService extraCostService;
    private final TruckPartsService truckPartsService;
    private final ExpeditionService expeditionService;

    public ExpeditionSummaryController(ExpeditionSummaryService expeditionSummaryService, FuelService fuelService, ExtraCostService extraCostService, TruckPartsService truckPartsService, ExpeditionService expeditionService){
        this.expeditionSummaryService = expeditionSummaryService;
        this.fuelService = fuelService;
        this.extraCostService = extraCostService;
        this.truckPartsService = truckPartsService;
        this.expeditionService = expeditionService;
    }

    //=========SUMMARY OF EXPEDITION COSTS FOR THE VIEW OF SHIPPER ========
    @GetMapping(value="/expedition/summary/{idExpedition}")
    public ModelAndView getSummaryOfExpeditionCostsView(@PathVariable Long idExpedition){
        Optional<Expedition> expeditionOptional = expeditionService.getExpeditionById(idExpedition);
        Expedition expedition = expeditionOptional.get(); //nie wiem czy mam tu dawac if z isPresent() czy po prostu to pominać
        ExpeditionSummary expeditionSummary = expeditionSummaryService.getSummaryOfExpedition(expedition);
        ModelAndView modelAndView = new ModelAndView("summary");
        modelAndView.addObject("expeditionSummary", expeditionSummary.toString());
        return modelAndView;
    }
//
//    @GetMapping("/download")
//    public ResponseEntity<byte[]> downloadErrorData(@PathVariable Long idExpedition) throws Exception {
//        Optional<Expedition> expeditionOptional = expeditionService.getExpeditionById(idExpedition);
//        Expedition expedition = expeditionOptional.get();
//        ExpeditionSummary expeditionSummary = expeditionSummaryService.getSummaryOfExpedition(expedition);
//
//        final CsvMapper mapper = new CsvMapper();
//        final CsvSchema schema = mapper.schemaFor(Test.class);
//        final String fileWithExpeditionSummary = mapper.writer(schema.withUseHeader(true)).writeValueAsString(expeditionSummary);
//
//        byte[] isr = fileWithExpeditionSummary.getBytes();
//        String fileName = "expeditionSummary.csv";
//        HttpHeaders respHeaders = new HttpHeaders();
//        respHeaders.setContentLength(isr.length);
//        respHeaders.setContentType(new MediaType("text", "csv"));
//        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
//    }
}
