package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.repository;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.ExchangeRateAndCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyExchangeRatesRepository extends JpaRepository<ExchangeRateAndCode, Long> {

    Optional<ExchangeRateAndCode> findByCurrencyCodeAndDateOfExchangeRate(CurrencyCode currencyCode, LocalDate dateOfExchangeRate);
}
