package ru.povolzie.hakaton.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.event.Event;
import ru.povolzie.hakaton.model.geodata.GeoData;
import ru.povolzie.hakaton.model.point.PointOfInterest;
import ru.povolzie.hakaton.repository.AnalyzerRepository;
import ru.povolzie.hakaton.service.AnalyzerService;
import ru.povolzie.hakaton.service.PointOfInterestService;

@Service
@Slf4j
@AllArgsConstructor
public class AnalyzerServiceImpl implements AnalyzerService {

  PointOfInterestService pointOfInterestService;

  AnalyzerRepository analyzerRepository;

  @Override
  public List<Event> create(GeoData geoData) {
    log.info("create offers");
    List<PointOfInterest> points = pointOfInterestService.getNear(geoData.getLatitude(), geoData.getLongitude());
    List<Event> events = new ArrayList<>();
    if (points != null) {
      for (PointOfInterest point : points) {
        Event event = new Event(null, geoData.getClientId(), point, geoData.getTime());
        events.add(create(event));
      }
    }
    return events;
  }

  @Override
  public Event create(Event event) {
    log.info("create offer");
    return analyzerRepository.save(event);
  }
}
