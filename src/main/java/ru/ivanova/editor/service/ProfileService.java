package ru.ivanova.editor.service;

import java.util.List;
import java.util.Optional;
import ru.ivanova.editor.model.Profile;

public interface ProfileService {
  Profile create(Profile profile);

  List<Profile> getAll();

  Profile getLast();

  Optional<Profile> findById(Long aId);

  Optional<Profile> findByEmail(String aEmail);
}
