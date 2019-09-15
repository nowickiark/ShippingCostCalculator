package com.sda.groupa.shippingcostcalculator.extraCosts.repository;


import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.expedition.model.Expedition;
import com.sda.groupa.shippingcostcalculator.extraCosts.model.ExtraCost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ExtraCostRepository extends JpaRepository<ExtraCost, Long> {

    List<ExtraCost> findExtraCostByExpedition(Expedition expedition);
    List<ExtraCost> findExtraCostByExpeditionAndCurrencyCode(Expedition expedition, CurrencyCode currencyCode);


}
