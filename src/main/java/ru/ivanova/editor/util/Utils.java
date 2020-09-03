package ru.ivanova.editor.util;

import java.util.Date;
import ru.ivanova.editor.dto.ProfileDto;
import ru.ivanova.editor.model.Profile;

public class Utils {

  public static Profile toProfile(ProfileDto profileDto) {
    return toLowerCase(new Profile(null, profileDto.getName(), profileDto.getEmail(), profileDto.getAge(), new Date()));
  }

  private static Profile toLowerCase(Profile profile) {
    profile.setEmail(profile.getEmail().toLowerCase());
    profile.setName(profile.getName().toLowerCase());
    return profile;
  }

  private Utils() {
  }

  ;
}
