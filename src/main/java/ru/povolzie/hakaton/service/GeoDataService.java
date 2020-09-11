package ru.povolzie.hakaton.service;

import java.util.List;
import java.util.Optional;
import ru.povolzie.hakaton.model.GeoData;

public interface GeoDataService {
  GeoData create(GeoData geoData);

  List<GeoData> getAll();

  GeoData getLast();

  Optional<GeoData> findById(Long aId);

  Optional<GeoData> findByEmail(String aEmail);
}
