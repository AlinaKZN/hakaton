package ru.povolzie.hakaton.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
  @NotNull
  private Long clientId;

  private PointOfInterest point;
}
