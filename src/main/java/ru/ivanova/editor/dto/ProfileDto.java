package ru.ivanova.editor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {
  private String name;

  private String email;

  private int age;
}
