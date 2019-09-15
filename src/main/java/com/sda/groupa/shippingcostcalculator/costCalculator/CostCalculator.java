package com.sda.groupa.shippingcostcalculator.costCalculator;



import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

import java.math.BigDecimal;

public interface CostCalculator {

    //====calculates all costs payed in currencies other than the choosen one(given in parameter)====

    //BigDecimal calculateSumOfCostsInCurrencyOtherThanPLN(CurrencyCode currencyCode, Expedition expedition); <-method is private in FuelRepository


    //====calculates all costs payed in choosen currency (given in parameter)====

    BigDecimal calculateSumOfCostsInCurrencyOf(CurrencyCode currencyCode, Expedition expedition);

//    CostType getSupportedCostType();
}
