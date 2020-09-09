package ru.ivanova.editor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ivanova.editor.model.ErrorMessage;
import ru.ivanova.editor.service.ErrorService;

@RestController
@AllArgsConstructor
@Slf4j
public class ErrorController {

  private ErrorService errorService;

  @GetMapping(value = "/error/last")
  public ResponseEntity getLast() {
    log.info("/error/last");
    ErrorMessage message = errorService.getMessage();
    return (message == null) ? new ResponseEntity(HttpStatus.NOT_FOUND) : ResponseEntity.ok(message);
  }
}
