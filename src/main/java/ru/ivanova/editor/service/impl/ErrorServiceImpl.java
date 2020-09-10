package ru.ivanova.editor.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ivanova.editor.dto.ErrorMessageDto;
import ru.ivanova.editor.model.ErrorMessage;
import ru.ivanova.editor.repository.ErrorRepository;
import ru.ivanova.editor.service.ErrorService;
import ru.ivanova.editor.util.Utils;

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
