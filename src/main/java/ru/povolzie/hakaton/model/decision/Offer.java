package ru.povolzie.hakaton.model.decision;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Entity
@Table(name = "decision")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Offer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Long clientId;

  @OneToOne
  private PointOfInterest point;
}
