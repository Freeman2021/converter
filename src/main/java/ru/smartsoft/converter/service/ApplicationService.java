package ru.smartsoft.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartsoft.converter.dto.CalculateConversionDTO;
import ru.smartsoft.converter.dto.ConversionHistoryDTO;
import ru.smartsoft.converter.dto.CurrencyDTO;
import ru.smartsoft.converter.entity.ConversionHistory;
import ru.smartsoft.converter.entity.Currency;
import ru.smartsoft.converter.repository.ConversionHistoryRepository;
import ru.smartsoft.converter.repository.CurrencyRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ConversionHistoryRepository conversionHistoryRepository;

    public List<CurrencyDTO> getCurrencyDTOList() {
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList.stream().map(CurrencyDTO::new).collect(Collectors.toList());
    }

    public List<ConversionHistoryDTO> getConversionHistoryDTOList() {
        List<ConversionHistory> conversionHistoryList = conversionHistoryRepository.findAll();
        return conversionHistoryList.stream().map(ConversionHistoryDTO::new).collect(Collectors.toList());
    }

    public List<ConversionHistoryDTO> getFilteredConversionHistoryDTOList(LocalDate date, String sourceCharCode, String targetCharCode) {
        List<ConversionHistory> filteredConversionHistoryList = conversionHistoryRepository.findByDateAndSourceCurrencyCharCodeAndTargetCurrencyCharCode(date, sourceCharCode, targetCharCode);
        return filteredConversionHistoryList.stream().map(ConversionHistoryDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public BigDecimal getTargetCurrencyAmount (CalculateConversionDTO calculateConversionDTO) {
        Currency sourceCurrency = currencyRepository.findByCharCode(calculateConversionDTO.getSourceCurrencyCharCode());
        Currency targetCurrency = currencyRepository.findByCharCode(calculateConversionDTO.getTargetCurrencyCharCode());

        /*
        * targetCurrencyAmount =
        * = ((sourceCurrencyValue / sourceCurrencyNominal) / (targetCurrencyValue / targetCurrencyNominal)) * sourceCurrencyAmount =
        * = ((sourceCurrencyValue * targetCurrencyNominal) / (sourceCurrencyNominal * targetCurrencyValue)) * sourceCurrencyAmount
        */

        BigDecimal firstRoundBrackets = sourceCurrency.getCurrencyValue().getValue()
                .multiply(new BigDecimal(targetCurrency.getNominal()));

        BigDecimal secondRoundBrackets = new BigDecimal(sourceCurrency.getNominal())
                .multiply(targetCurrency.getCurrencyValue().getValue());

        BigDecimal thirdRoundBrackets = firstRoundBrackets
                .divide(secondRoundBrackets, RoundingMode.HALF_UP);

        BigDecimal targetCurrencyAmount = thirdRoundBrackets
                .multiply(calculateConversionDTO.getSourceCurrencyAmount());

        saveConversionHistory(sourceCurrency, targetCurrency, calculateConversionDTO.getSourceCurrencyAmount(), targetCurrencyAmount);
        return targetCurrencyAmount;
    }

    private void saveConversionHistory(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceCurrencyAmount, BigDecimal targetCurrencyAmount) {
        ConversionHistory conversionHistory = ConversionHistory.builder()
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .sourceCurrencyAmount(sourceCurrencyAmount)
                .targetCurrencyAmount(targetCurrencyAmount)
                .date(LocalDate.now())
                .build();
        conversionHistoryRepository.save(conversionHistory);
    }
}
