package ru.povolzie.hakaton.util;

import java.util.Date;
import ru.povolzie.hakaton.dto.ErrorMessageDto;
import ru.povolzie.hakaton.dto.GeoDataDto;
import ru.povolzie.hakaton.model.ErrorMessage;
import ru.povolzie.hakaton.model.GeoData;

public class Utils {

  public static GeoData toGeoData(GeoDataDto geoDataDto) {
    return toLowerCase(new GeoData(null, geoDataDto.getName(), geoDataDto.getEmail(), geoDataDto.getAge(), new Date()));
  }

  private static GeoData toLowerCase(GeoData geoData) {
    geoData.setEmail(geoData.getEmail().toLowerCase());
    geoData.setName(geoData.getName().toLowerCase());
    return geoData;
  }

  public static ErrorMessageDto toMessageDto(ErrorMessage errorMessage) {
    return (errorMessage == null) ? null : new ErrorMessageDto(errorMessage.getMessage(), errorMessage.getCreated());
  }

  private Utils() {
  }
}
