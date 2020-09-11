package ru.povolzie.hakaton.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessageDto {

  @NotNull
  private String msg;

  @CreatedDate
  private Date created;
}
