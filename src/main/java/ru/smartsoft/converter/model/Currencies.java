package ru.smartsoft.converter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currencies",
        indexes = @Index(name = "char_code_index", columnList = "char_code", unique = true))
public class Currencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "char_code")
    private String charCode;

    @Column(name = "nominal")
    private long nominal;

    @OneToOne
    private CurrenciesValue value;
}
