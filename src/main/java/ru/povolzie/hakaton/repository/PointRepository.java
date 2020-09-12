package ru.povolzie.hakaton.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Repository
public interface PointRepository extends JpaRepository<PointOfInterest, Long> {

  @Query(value = "select * from point_of_interest poi where ((:x-poi.latitude)^2+(:y-poi.longitude)^2)<100", nativeQuery = true)
  public List<PointOfInterest> getNear(Float x, Float y);
}
