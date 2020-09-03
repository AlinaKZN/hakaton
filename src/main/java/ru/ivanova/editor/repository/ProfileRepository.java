package ru.ivanova.editor.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanova.editor.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Profile findTopByOrderByCreatedAsc();

  Optional<Profile> findByEmailIgnoreCase(String aEmail);
}
