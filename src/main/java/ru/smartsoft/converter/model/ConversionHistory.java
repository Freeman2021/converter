package ru.smartsoft.converter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_currency_amount")
    private BigDecimal sourceCurrencyAmount;

    @Column(name = "target_currency_amount")
    private BigDecimal targetCurrencyAmount;

    @Column(name = "date")
    private Date date;

    @OneToOne
    private Currencies sourceCurrency;

    @OneToOne
    private Currencies targetCurrency;
}
