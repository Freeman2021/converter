package ru.smartsoft.converter.dto;

import lombok.Data;
import ru.smartsoft.converter.entity.Currency;

@Data
public class CurrencyDTO {

    private String charCode;

    private String name;

    public CurrencyDTO(Currency currency) {
        this.charCode = currency.getCharCode();
        this.name = currency.getName();
    }
}
