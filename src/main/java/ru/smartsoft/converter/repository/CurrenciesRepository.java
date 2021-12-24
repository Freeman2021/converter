package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.model.Currencies;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {
}
