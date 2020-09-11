package ru.povolzie.hakaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.povolzie.hakaton.model.ErrorMessage;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorMessage, Long> {
  ErrorMessage findTopByOrderByCreatedAsc();
}
