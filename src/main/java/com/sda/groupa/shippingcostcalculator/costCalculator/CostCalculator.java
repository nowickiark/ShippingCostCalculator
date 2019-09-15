package com.sda.groupa.shippingcostcalculator.costCalculator;



import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;

import java.math.BigDecimal;

public interface CostCalculator {

    //====calculates all costs payed in PLN=======
    BigDecimal calculateSumOfCostsPayedInCurrencyOfPLN (Expedition expedition);

    //====calculates all costs payed in choosen currency (given in parameter)====
    BigDecimal calculateSumOfCostsPayedInCurrencyOf(CurrencyCode currencyCode, Expedition expedition);

    //====calculates all costs payed in all currencies other than PLN (given in parameter)====
    BigDecimal calculateSumOfCostsPayedInAllCurrenciesOtherThanPLN(Expedition expedition);
}
