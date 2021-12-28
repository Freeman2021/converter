package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.entity.CurrencyValue;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface CurrenciesValueRepository extends JpaRepository<CurrencyValue, Long> {

    boolean existsByDate(LocalDate date);

}
