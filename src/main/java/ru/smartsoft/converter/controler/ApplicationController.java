package ru.smartsoft.converter.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.converter.configuration.ApplicationConfiguration;
import ru.smartsoft.converter.dto.ConversionHistoryDTO;
import ru.smartsoft.converter.entity.ConversionHistory;
import ru.smartsoft.converter.entity.Currency;
import ru.smartsoft.converter.repository.ConversionHistoryRepository;
import ru.smartsoft.converter.repository.CurrenciesRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    CurrenciesRepository currenciesRepository;

    @Autowired
    ConversionHistoryRepository conversionHistoryRepository;

    @Autowired
    ApplicationConfiguration applicationConfiguration;

    @GetMapping(path = "/currencyList")
    public ResponseEntity<?> getCurrencies() {
        List<Currency> currencyList = currenciesRepository.findAll();
        List<ApplicationConfiguration.Pair> pairList = new ArrayList<>();
        for (Currency currency: currencyList){
            pairList.add(applicationConfiguration.getPair(currency.getCharCode(), currency.getName()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationConfiguration.getGson().toJson(pairList));
    }

    @GetMapping(path = "/conversionHistory")
    public ResponseEntity<?> getConversionHistory() {
        List<ConversionHistory> conversionHistoryList = conversionHistoryRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationConfiguration.getGson().toJson(conversionHistoryList));
    }

    @ResponseBody
    @PostMapping(path = "/calculateAndSave")
    public ResponseEntity<?> calculateAndSaveConversion(@RequestBody ConversionHistoryDTO conversionHistoryDTO) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationConfiguration.getGson().toJson(conversionHistoryDTO));
    }
}
