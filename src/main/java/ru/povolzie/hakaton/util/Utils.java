package ru.povolzie.hakaton.util;

import ru.povolzie.hakaton.dto.GeoDataDto;
import ru.povolzie.hakaton.model.geodata.GeoData;

public class Utils {

  public static GeoData toGeoData(GeoDataDto geoDataDto) {
    return new GeoData();//null, geoDataDto.getName(), geoDataDto.getEmail(), geoDataDto.getAge(), new Date());
  }
}
