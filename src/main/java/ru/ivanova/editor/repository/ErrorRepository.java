package ru.ivanova.editor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanova.editor.model.ErrorMessage;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorMessage, Long> {
  ErrorMessage findTopByOrderByCreatedAsc();
}
