package ru.povolzie.hakaton.model.event;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.povolzie.hakaton.model.point.PointOfInterest;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Long clientId;

  @OneToOne
  private PointOfInterest point;

  @CreatedDate
  private Date created;
}
