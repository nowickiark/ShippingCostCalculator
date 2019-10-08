package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.dailyRate.DailyRate;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.exception.NoLatestCurrencyReachedException;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.jsonReader.JsonReader;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.ExchangeRateAndCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.repository.CurrencyExchangeRatesRepository;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyRateService {
    private final CurrencyExchangeRatesRepository currencyExchangeRatesRepository;

    private final String basicURL = "http://api.nbp.pl/api/exchangerates/rates/a/";
    public CurrencyRateService (CurrencyExchangeRatesRepository currencyExchangeRatesRepository){
        this.currencyExchangeRatesRepository  = currencyExchangeRatesRepository;

    }

    private DailyRate getDailyRateObjectFromJason(String url_str) throws IOException {
        JsonReader jsonReader = new JsonReader();
        InputStream inputStream = new URL(url_str).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonReader.readAll(bufferedReader), DailyRate.class);

    }

    private ExchangeRateAndCode getLatestCurrencyExchangeRateAndCodeFromAPI(CurrencyCode currencyCode) throws IOException {
        String code = currencyCode.toString().toLowerCase();
        String url_str = basicURL + code + "?format=json";

        DailyRate dailyRate = getDailyRateObjectFromJason(url_str);
        BigDecimal midRate = dailyRate.getRates().get(0).getMid();
        String exchangeRateDate = dailyRate.getRates().get(0).getEffectiveDate();
        LocalDate dateOfExchangeRate = LocalDate.parse(exchangeRateDate);

        ExchangeRateAndCode exchangeRateAndCode = new ExchangeRateAndCode(dateOfExchangeRate, currencyCode, midRate);
        currencyExchangeRatesRepository.save(exchangeRateAndCode);
        return exchangeRateAndCode;
    }
    public void checkLatestCurrencyExchangeRate(CurrencyCode currencyCode, LocalDate dateOfPayment) throws IOException {
        if(!currencyCode.equals(CurrencyCode.PLN)) {
            Optional<ExchangeRateAndCode> optionalExchangeRateAndCode = currencyExchangeRatesRepository
                    .findByCurrencyCodeAndAndDateOfExchangeRate(currencyCode, dateOfPayment);

            if (!optionalExchangeRateAndCode.isPresent()) {
                getLatestCurrencyExchangeRateAndCodeFromAPI(currencyCode).getCurrencyRate();
            }
        }
    }

    public BigDecimal getLatestCurrencyExchangeRate (CurrencyCode currencyCode, LocalDate dateOfPayment) throws IOException {
        Optional<ExchangeRateAndCode> optionalExchangeRateAndCode = currencyExchangeRatesRepository
                .findByCurrencyCodeAndAndDateOfExchangeRate(currencyCode, dateOfPayment);

        if(optionalExchangeRateAndCode.isPresent()){
            ExchangeRateAndCode exchangeRateAndCode = optionalExchangeRateAndCode.get();
            return exchangeRateAndCode.getCurrencyRate();
        }
        return getLatestCurrencyExchangeRateAndCodeFromAPI(currencyCode).getCurrencyRate();
    }

    public BigDecimal calculateCostInPLNofSingleExpensePayedInForeignCurrency (BigDecimal costOfSingleCost, CurrencyCode currencyCode, LocalDate dateOfPayment){
        try {
            BigDecimal latestCurrencyExchangeRate = getLatestCurrencyExchangeRate(currencyCode, dateOfPayment);
            return costOfSingleCost.multiply(latestCurrencyExchangeRate);
        } catch (IOException e) {
            throw  new NoLatestCurrencyReachedException();
        }
    }



}
