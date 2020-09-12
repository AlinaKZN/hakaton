package ru.povolzie.hakaton.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import ru.povolzie.hakaton.model.point.PointOfInterest;
import ru.povolzie.hakaton.service.PointOfInterestService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = PointOfInterestController.REST_URL)
public class PointOfInterestController {
  static final String REST_URL = "/points";

  private PointOfInterestService pointService;

  @PostMapping(value = "/setList", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity setPointsOfInterestList(@RequestBody PointOfInterest[] points) {
    List<PointOfInterest> response = new ArrayList<>();
    for (PointOfInterest point : points) {
      PointOfInterest createdPoint = pointService.create(point);
      response.add(createdPoint);
      log.info("Saved point of interest: " + createdPoint.toString());
    }
    return ResponseEntity.ok(points.length);
  }

  @PostMapping(value = "/set", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity create(@RequestBody PointOfInterest point) {
    log.info(REST_URL + "/set {}", point);
    PointOfInterest createdPoint = pointService.create(point);
    return ResponseEntity.ok(point.getId());
  }

  @GetMapping
  public ResponseEntity getAllProfiles() {
    log.info(REST_URL);
    List<PointOfInterest> geoData = pointService.getAll();
    return ResponseEntity.ok(geoData);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity get(@PathVariable Long id) {
    log.info(REST_URL + "/{}", id);
    Optional<PointOfInterest> point = pointService.findById(id);
    /*if (!point.isPresent()) {
      return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }*/
    return ResponseEntity.ok(point);
  }


 /* @PostMapping(value = "/get")
  public ResponseEntity getByName(@RequestBody GeoDataDto geoDataDto) {
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
