package ru.smartsoft.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartsoft.converter.entity.ConversionHistory;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
}
