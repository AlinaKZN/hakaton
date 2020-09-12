package ru.povolzie.hakaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.povolzie.hakaton.model.event.Event;

@Repository
public interface AnalyzerRepository extends JpaRepository<Event, Long> {
}
