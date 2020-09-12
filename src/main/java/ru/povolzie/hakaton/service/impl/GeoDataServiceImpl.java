package ru.povolzie.hakaton.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.geodata.GeoData;
import ru.povolzie.hakaton.repository.GeoDataRepository;
import ru.povolzie.hakaton.service.GeoDataService;

@Service
@Slf4j
@AllArgsConstructor
public class GeoDataServiceImpl implements GeoDataService {

  private GeoDataRepository geoDataRepository;

  @Override
  public GeoData create(GeoData geoData) {
    GeoData createdGeoData = geoDataRepository.save(geoData);
    log.info("Created geoData {}", createdGeoData);
    return createdGeoData;
  }

  @Override
  public List<GeoData> getAll() {
    List<GeoData> geoData = geoDataRepository.findAll();
    log.info("Found {} geoData", geoData.size());
    return geoData;
  }

/*  @Override
  public GeoData getLast() {
    GeoData geoData = geoDataRepository.findTopByOrderByCreatedDesc();
    log.info("Last geoData is {}", geoData);
    return geoData;
  }*/

  @Override
  public Optional<GeoData> findById(Long aId) {
    Optional<GeoData> profile = geoDataRepository.findById(aId);
    log.info("geoData with id={} is {}", aId, profile);
    return profile;
  }
/*
  @Override
  public Optional<GeoData> findByEmail(String aEmail) {
    Optional<GeoData> profile = geoDataRepository.findByEmailIgnoreCase(aEmail);
    log.info("Profile with email={} is {}", aEmail, profile);
    return profile;
  }*/
}
