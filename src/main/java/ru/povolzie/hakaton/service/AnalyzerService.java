package ru.povolzie.hakaton.service;

import java.util.List;
import ru.povolzie.hakaton.model.decision.Offer;
import ru.povolzie.hakaton.model.geodata.GeoData;

public interface AnalyzerService {
  List<Offer> create(GeoData geoData);

  Offer create(Offer offer);
}
