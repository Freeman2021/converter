package ru.smartsoft.converter.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.converter.dto.ConversionHistoryDTO;
import ru.smartsoft.converter.dto.CurrencyDTO;
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

    @GetMapping(path = "/currencyList")
    public ResponseEntity<List<CurrencyDTO>> getCurrencies() {
        List<Currency> currencyList = currenciesRepository.findAll();
        List<CurrencyDTO> currencyDTOList = new ArrayList<>();
        for (Currency currency: currencyList){
            currencyDTOList.add(new CurrencyDTO(currency.getCharCode(), currency.getName()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currencyDTOList);
    }

    @GetMapping(path = "/conversionHistory")
    public ResponseEntity<List<ConversionHistoryDTO>> getConversionHistory() {
        List<ConversionHistory> conversionHistoryList = conversionHistoryRepository.findAll();
        List<ConversionHistoryDTO> conversionHistoryDTOList = new ArrayList<>();
        for (ConversionHistory conversionHistory : conversionHistoryList){
            conversionHistoryDTOList.add(ConversionHistoryDTO.builder()
                    .sourceCurrencyAmount(conversionHistory.getSourceCurrencyAmount())
                    .targetCurrencyAmount(conversionHistory.getTargetCurrencyAmount())
                    .date(conversionHistory.getDate())
                    .sourceCurrencyCharCode(conversionHistory.getSourceCurrency().getCharCode())
                    .targetCurrencyCharCode(conversionHistory.getTargetCurrency().getCharCode())
                    .build());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(conversionHistoryDTOList);
    }

    @GetMapping(path = "/filterConversionHistory")
    public ResponseEntity<?> getFilteredConversionHistory(@RequestParam(required = false) String date, @RequestParam(required = false) String sourceCharCode, @RequestParam(required = false) String targetCharCode) {
        Object[] arr = new Object[3];
        arr[0] = date;
        arr[1] = sourceCharCode;
        arr[2] = targetCharCode;
/*
        var fff = conversionHistoryRepository.findByDateAndSourceCurrencyCharCodeAndTargetCurrencyCharCode(new SimpleDateFormat("dd.MM.yyyy").parse(date), sourceCharCode, targetCharCode);
*/
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(arr);
    }

    @ResponseBody
    @PostMapping(path = "/calculateAndSave")
    public ResponseEntity<?> calculateAndSaveConversion(@RequestBody ConversionHistoryDTO conversionHistoryDTO) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(conversionHistoryDTO);
    }
}
