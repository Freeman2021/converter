package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCharCode (String charCode);

    @Query("SELECT COUNT(*) FROM Currency")
    int countAll();

}
