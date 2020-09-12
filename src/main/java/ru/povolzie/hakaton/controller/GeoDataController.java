package ru.povolzie.hakaton.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.dto.GeoDataDto;
import ru.povolzie.hakaton.message.Messages;
import ru.povolzie.hakaton.model.geodata.GeoData;
import ru.povolzie.hakaton.service.GeoDataService;
import ru.povolzie.hakaton.util.Utils;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = GeoDataController.REST_URL)
public class GeoDataController {
  static final String REST_URL = "/geodata";

  private GeoDataService geoDataService;

  @PostMapping(value = "/set", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity create(@RequestBody GeoDataDto geoData) {
    log.info(REST_URL + "/set {}", geoData);
    GeoData createdGeoData = geoDataService.create(Utils.toGeoData(geoData));
    return ResponseEntity.ok(createdGeoData.getId());
  }

 /* @GetMapping(value = "/last")
  public ResponseEntity getLastProfile() {
    log.info(REST_URL + "/last");
    GeoData geoData = geoDataService.getLast();
    if (geoData != null) {
      return ResponseEntity.ok(geoData);
    }
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }*/

  @GetMapping
  public ResponseEntity getAllProfiles() {
    log.info(REST_URL);
    List<GeoData> geoData = geoDataService.getAll();
    return ResponseEntity.ok(geoData);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity getGeoData(@PathVariable Long id) {
    log.info(REST_URL + "/{}", id);
    Optional<GeoData> geoData = geoDataService.findById(id);
    if (!geoData.isPresent()) {
      Map<String, Object> body = Map.of("msg", Messages.PROFILE_WITH_EMAIL_NOTFOUND);
      return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(geoData);
  }

 /* @PostMapping(value = "/get")
  public ResponseEntity getGeoData(@RequestBody GeoDataDto geoDataDto) {
    String email = geoDataDto.getEmail();
    log.info(REST_URL + "/get/{}", email);
    Optional<GeoData> profile = geoDataService.findByEmail(email);
    if (!profile.isPresent()) {
      errorService.log(Messages.PROFILE_WITH_ID_NOTFOUND);
      Map<String, Object> body = Map.of("message", Messages.PROFILE_WITH_EMAIL_NOTFOUND);
      return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(profile);
  }*/
}
