package ru.smartsoft.converter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate date;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Currency sourceCurrency;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Currency targetCurrency;
}
