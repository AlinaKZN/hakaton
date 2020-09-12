package ru.povolzie.hakaton.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.point.PointOfInterest;
import ru.povolzie.hakaton.repository.PointRepository;
import ru.povolzie.hakaton.service.PointOfInterestService;

@Service
@Slf4j
@AllArgsConstructor
public class PointOfInterestServiceImpl implements PointOfInterestService {

  private PointRepository pointRepository;

  @Override
  public PointOfInterest create(PointOfInterest point) {
    PointOfInterest createdPoint = pointRepository.save(point);
    log.info("Created point {}", createdPoint);
    return createdPoint;
  }

  @Override
  public List<PointOfInterest> getAll() {
    List<PointOfInterest> point = pointRepository.findAll();
    log.info("Found {} profiles", point.size());
    return point;
  }

  @Override
  public Optional<PointOfInterest> findById(Long aId) {
    Optional<PointOfInterest> point = pointRepository.findById(aId);
    log.info("Point with id={} is {}", aId, point);
    return point;
  }
}
