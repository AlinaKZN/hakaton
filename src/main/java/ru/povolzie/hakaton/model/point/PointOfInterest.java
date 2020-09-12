package ru.povolzie.hakaton.model.point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "point_of_interest")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointOfInterest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Float latitude;

  @NotNull
  private Float longitude;

  @NotNull
  private String type;
}
