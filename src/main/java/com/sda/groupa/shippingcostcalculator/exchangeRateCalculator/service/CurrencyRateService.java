package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.service;

import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.jsonReader.JsonReader;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.CurrencyCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.model.ExchangeRateAndCode;
import com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.repository.CurrencyExchangeRatesRepository;
import com.sda.groupa.shippingcostcalculator.fuel.fuelModel.Fuel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyRateService {
    private final CurrencyExchangeRatesRepository currencyExchangeRatesRepository;

    public CurrencyRateService (CurrencyExchangeRatesRepository currencyExchangeRatesRepository){
        this.currencyExchangeRatesRepository  = currencyExchangeRatesRepository;

    }


    private ExchangeRateAndCode getLatestCurrencyExchangeRateAndCodeFromAPI(Fuel fuel) throws IOException, JSONException {

        CurrencyCode currencyCode = fuel.getCurrencyCode();

        String basicURL = "http://api.nbp.pl/api/exchangerates/rates/a/";
        String code = currencyCode.toString().toLowerCase();
        JsonReader jsonReader = new JsonReader();

        String url_str = basicURL + code + "?format=json";

        JSONObject dailyRates = jsonReader.readJsonFromUrl(url_str);
        JSONArray listrOfRates = new JSONArray(dailyRates.get("rates").toString());
        JSONObject rate = new JSONObject(listrOfRates.get(0).toString());

        Double mid = rate.getDouble("mid");
        BigDecimal midRate = new BigDecimal(mid);

        String exchangeRateDate = rate.getString("effectiveDate");
        LocalDate dateOfExchangeRate = LocalDate.parse(exchangeRateDate);

        ExchangeRateAndCode exchangeRateAndCode = new ExchangeRateAndCode(dateOfExchangeRate, currencyCode, midRate);

        currencyExchangeRatesRepository.save(exchangeRateAndCode);

        return exchangeRateAndCode;
    }


    public BigDecimal getLatestCurrencyExchangeRate (Fuel fuel) throws IOException, JSONException {

        BigDecimal exchangeRate;

        LocalDate dateOfFueling = fuel.getDateOfFueling();
        CurrencyCode currencyCode = fuel.getCurrencyCode();

        Optional<ExchangeRateAndCode> optionalExchangeRateAndCode = currencyExchangeRatesRepository
                .findByCurrencyCodeAndAndDateOfExchangeRate(currencyCode, dateOfFueling);

        if(optionalExchangeRateAndCode.isPresent()){
            ExchangeRateAndCode exchangeRateAndCode = optionalExchangeRateAndCode.get();
            exchangeRate = exchangeRateAndCode.getCurrencyRate();

        }else {
            exchangeRate = getLatestCurrencyExchangeRateAndCodeFromAPI(fuel).getCurrencyRate();

        }
        return exchangeRate;

    }

}
