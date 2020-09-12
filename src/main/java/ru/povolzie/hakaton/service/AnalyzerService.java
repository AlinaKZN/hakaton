package ru.povolzie.hakaton.service;

import java.util.List;
import ru.povolzie.hakaton.model.event.Event;
import ru.povolzie.hakaton.model.geodata.GeoData;

public interface AnalyzerService {
  List<Event> create(GeoData geoData);

  Event create(Event event);
}
