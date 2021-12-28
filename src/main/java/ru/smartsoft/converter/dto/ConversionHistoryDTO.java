package ru.smartsoft.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryDTO {

    private BigDecimal sourceCurrencyAmount;

    private BigDecimal targetCurrencyAmount;

    private LocalDate date;

    private String sourceCurrencyCharCode;

    private String targetCurrencyCharCode;

}
