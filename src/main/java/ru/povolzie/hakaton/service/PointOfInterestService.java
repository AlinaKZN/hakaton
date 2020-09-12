package ru.povolzie.hakaton.service;

import java.util.List;
import java.util.Optional;
import ru.povolzie.hakaton.model.point.PointOfInterest;

public interface PointOfInterestService {
  PointOfInterest create(PointOfInterest point);

  List<PointOfInterest> getAll();

  Optional<PointOfInterest> findById(Long aId);

  List<PointOfInterest> getNear(Float latitude, Float longitude);
}
