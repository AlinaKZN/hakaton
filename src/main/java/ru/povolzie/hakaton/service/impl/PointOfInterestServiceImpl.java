package ru.povolzie.hakaton.service.impl;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.point.PointOfInterest;
import ru.povolzie.hakaton.repository.PointRepository;
import ru.povolzie.hakaton.service.PointOfInterestService;
import ru.povolzie.hakaton.util.PointsParser;

@Service
@Slf4j
@AllArgsConstructor
public class PointOfInterestServiceImpl implements PointOfInterestService {

  private PointRepository pointRepository;

  @PostConstruct
  public void doTask() {
    if (getAll().isEmpty()) {
      List<PointOfInterest> pointsOfInterest = PointsParser.parse();
      pointsOfInterest.forEach(p -> create(p));
    }
  }

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

  @Override
  public List<PointOfInterest> getNear(Float latitude, Float longitude) {
    List<PointOfInterest> points = pointRepository.getNear(latitude, longitude);

    return points;
  }
}
