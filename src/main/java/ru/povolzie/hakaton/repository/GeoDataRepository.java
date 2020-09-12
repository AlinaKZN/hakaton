package ru.povolzie.hakaton.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.povolzie.hakaton.model.geodata.GeoData;

@Repository
public interface GeoDataRepository extends JpaRepository<GeoData, Long> {

  GeoData findTopByOrderByCreatedDesc();

  Optional<GeoData> findByEmailIgnoreCase(String aEmail);
}
