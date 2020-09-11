package ru.povolzie.hakaton.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoDataDto {
  private String name;

  private String email;

  private int age;
}
