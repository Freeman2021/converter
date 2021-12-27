package ru.smartsoft.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryDTO {

    private BigDecimal sourceCurrencyAmount;

    private BigDecimal targetCurrencyAmount;

    private Date date;

    private String sourceCurrencyCharCode;

    private String targetCurrencyCharCode;

}
