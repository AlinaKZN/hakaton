package ru.ivanova.editor.service;

import ru.ivanova.editor.dto.ErrorMessageDto;

public interface ErrorService {

  ErrorMessageDto getMessage();

  void log(String message);
}
