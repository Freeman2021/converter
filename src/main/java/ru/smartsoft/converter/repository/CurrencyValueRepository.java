package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.entity.CurrencyValue;

import java.time.LocalDate;

@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue, Long> {

    boolean existsByDate(LocalDate date);

}
