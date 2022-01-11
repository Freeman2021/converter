package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.entity.ConversionHistory;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    List<ConversionHistory> findByDateAndSourceCurrencyCharCodeAndTargetCurrencyCharCode(LocalDate date, String sourceCharCode, String targetCharCode);

}
