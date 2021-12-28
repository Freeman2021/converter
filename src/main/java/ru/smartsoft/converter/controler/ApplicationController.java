package ru.smartsoft.converter.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.converter.dto.CalculateConversionDTO;
import ru.smartsoft.converter.dto.ConversionHistoryDTO;
import ru.smartsoft.converter.dto.CurrencyDTO;
import ru.smartsoft.converter.service.ApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(path = "/currencyList")
    public List<CurrencyDTO> getCurrencies() {
        return applicationService.getCurrencyDTOList();
    }

    @GetMapping(path = "/conversionHistory")
    public List<ConversionHistoryDTO> getConversionHistory() {
        return applicationService.getConversionHistoryDTOList();
    }

    @GetMapping(path = "/filterConversionHistory")
    public List<ConversionHistoryDTO> getFilteredConversionHistory(@RequestParam LocalDate date, @RequestParam String sourceCharCode, @RequestParam String targetCharCode) {
        return applicationService.getFilteredConversionHistoryDTOList(date, sourceCharCode, targetCharCode);
    }

    @PostMapping(path = "/calculateTargetCurrency")
    public BigDecimal calculateAndSaveConversion(@RequestBody CalculateConversionDTO calculateConversionDTO) {
        return applicationService.getTargetCurrencyAmount(calculateConversionDTO);
    }
}
