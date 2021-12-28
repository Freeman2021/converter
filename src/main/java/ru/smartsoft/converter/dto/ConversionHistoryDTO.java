package ru.smartsoft.converter.dto;

import lombok.Data;
import ru.smartsoft.converter.entity.ConversionHistory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ConversionHistoryDTO {

    private BigDecimal sourceCurrencyAmount;

    private BigDecimal targetCurrencyAmount;

    private LocalDate date;

    private String sourceCurrencyCharCode;

    private String targetCurrencyCharCode;

    public ConversionHistoryDTO(ConversionHistory conversionHistory) {
        this.sourceCurrencyAmount = conversionHistory.getSourceCurrencyAmount();
        this.targetCurrencyAmount = conversionHistory.getTargetCurrencyAmount();
        this.date = conversionHistory.getDate();
        this.sourceCurrencyCharCode = conversionHistory.getSourceCurrency().getCharCode();
        this.targetCurrencyCharCode = conversionHistory.getTargetCurrency().getCharCode();
    }
}
