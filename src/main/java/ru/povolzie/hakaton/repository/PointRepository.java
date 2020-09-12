package ru.povolzie.hakaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Repository
public interface PointRepository extends JpaRepository<PointOfInterest, Long> {
}
