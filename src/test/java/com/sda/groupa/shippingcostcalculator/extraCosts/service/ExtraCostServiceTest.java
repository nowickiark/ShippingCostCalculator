package com.sda.groupa.shippingcostcalculator.extraCosts.service;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service.CurrencyRateService;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import com.sda.groupa.shippingcostcalculator.extraCosts.repository.ExtraCostRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtraCostServiceTest {

    private ExtraCostRepository extraCostRepository = mock(ExtraCostRepository.class);
    private CurrencyRateService currencyRateService =mock(CurrencyRateService.class) ;

    
    @Test
    public void shouldCalculateSumOfCostsPayedInCurrencyOf(){
        //Given
        ExtraCostService systemUnderTest = new ExtraCostService(extraCostRepository, currencyRateService);
        Expedition expedition1 = new Expedition();
        expedition1.setId(1L);
        ExtraCost extraCost1 = new ExtraCost("wheel", BigDecimal.valueOf(200), CurrencyCode.PLN, LocalDate.of(2019, 9, 1), expedition1);
        ExtraCost extraCost2 = new ExtraCost("tyre", BigDecimal.valueOf(120), CurrencyCode.EUR, LocalDate.of(2019, 9, 6), expedition1);
        ExtraCost extraCost3 = new ExtraCost("window", BigDecimal.valueOf(60), CurrencyCode.PLN, LocalDate.of(2019, 9, 7), expedition1);
        ExtraCost extraCost4 = new ExtraCost("engine", BigDecimal.valueOf(300), CurrencyCode.EUR, LocalDate.of(2019, 9, 9), expedition1);
        List<ExtraCost> extraCostList = new ArrayList<>();
        extraCostList.add(extraCost1);
        extraCostList.add(extraCost2);
        extraCostList.add(extraCost3);
        extraCostList.add(extraCost4);
        when(extraCostRepository.findExtraCostByExpeditionAndCurrencyCode(expedition1, CurrencyCode.EUR)).thenReturn(extraCostList);
        when(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(BigDecimal.valueOf(120),CurrencyCode.EUR,LocalDate.of(2019, 9, 6)))
        .thenReturn(BigDecimal.valueOf(521.256));
        when(currencyRateService.calculateCostInPLNofSingleExpensePayedInForeignCurrency(BigDecimal.valueOf(300),CurrencyCode.EUR,LocalDate.of(2019, 9, 9)))
        .thenReturn(BigDecimal.valueOf(1303.14));

        BigDecimal expectedSum = new BigDecimal(1824.396).setScale(3, BigDecimal.ROUND_HALF_UP);
        //when
        BigDecimal actualSum = systemUnderTest.calculateSumOfCostsPayedInCurrencyOf(CurrencyCode.EUR, expedition1);
        //Then
        assertThat(actualSum).isEqualTo(expectedSum);
    }

}