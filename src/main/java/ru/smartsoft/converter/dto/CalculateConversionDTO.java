package ru.smartsoft.converter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CalculateConversionDTO {

    private String sourceCurrencyCharCode;

    private String targetCurrencyCharCode;

    private BigDecimal sourceCurrencyAmount;

}
