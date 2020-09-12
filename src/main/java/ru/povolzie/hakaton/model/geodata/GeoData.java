package ru.povolzie.hakaton.model.geodata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "geo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @JsonProperty("client_id")
  private Long clientId;

  @CreatedDate
  @JsonFormat(pattern = "dd.MM.yyyy' 'HH:mm")
  private Date time;

  @NotNull
  private Float latitude;

  @NotNull
  private Float longitude;

  @JsonProperty("altitude (m)")
  private Float altitude;

  @JsonProperty("speed (km/h)")
  private Float speed;

  private Float course;

  private Float sat;

  private String name;
}
