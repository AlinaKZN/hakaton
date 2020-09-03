package ru.ivanova.editor.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ivanova.editor.model.Profile;
import ru.ivanova.editor.repository.ProfileRepository;
import ru.ivanova.editor.service.ProfileService;

@Service
@Slf4j
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private ProfileRepository profileRepository;

  @Override
  public Profile create(Profile profile) {
    Profile createdProfile = profileRepository.save(profile);
    log.info("Created profile {}", createdProfile);
    return createdProfile;
  }

  @Override
  public List<Profile> getAll() {
    List<Profile> profiles = profileRepository.findAll();
    log.info("Found {} profiles", profiles.size());
    return profiles;
  }

  @Override
  public Profile getLast() {
    Profile profile = profileRepository.findTopByOrderByCreatedAsc();
    log.info("Last profile is {}", profile);
    return profile;
  }

  @Override
  public Optional<Profile> findById(Long aId) {
    Optional<Profile> profile = profileRepository.findById(aId);
    log.info("Profile with id={} is {}", aId, profile);
    return profile;
  }

  @Override
  public Optional<Profile> findByEmail(String aEmail) {
    Optional<Profile> profile = profileRepository.findByEmailIgnoreCase(aEmail);
    log.info("Profile with email={} is {}", aEmail, profile);
    return profile;
  }
}
