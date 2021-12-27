package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.model.CurrenciesValue;

@Repository
public interface CurrenciesValueRepository extends JpaRepository<CurrenciesValue, Long> {
}
