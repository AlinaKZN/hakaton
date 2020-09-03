package ru.ivanova.editor.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="error")
@AllArgsConstructor
@Data
public class ErrorMessage {

  @Id
  private Long id;

  private String message;

  @CreatedDate
  private Date created;
}
