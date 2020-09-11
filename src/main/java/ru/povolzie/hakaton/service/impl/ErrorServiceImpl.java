package ru.povolzie.hakaton.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.dto.ErrorMessageDto;
import ru.povolzie.hakaton.model.ErrorMessage;
import ru.povolzie.hakaton.repository.ErrorRepository;
import ru.povolzie.hakaton.service.ErrorService;
import ru.povolzie.hakaton.util.Utils;

@Service
@Slf4j
@AllArgsConstructor
public class ErrorServiceImpl implements ErrorService {

  private ErrorRepository errorRepository;

  @Override
  public ErrorMessageDto getMessage() {
    ErrorMessageDto errorMessageDto = Utils.toMessageDto(errorRepository.findTopByOrderByCreatedAsc());
    log.info("Last error is {}", errorMessageDto);
    return errorMessageDto;
  }

  @Override
  public void log(String message) {
    ErrorMessage errorMessage = new ErrorMessage(null, message, new Date());
    errorRepository.save(errorMessage);
    log.info("Created error is {}", errorMessage);
  }
}
