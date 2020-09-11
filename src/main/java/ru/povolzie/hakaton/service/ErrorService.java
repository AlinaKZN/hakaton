package ru.povolzie.hakaton.service;

import ru.povolzie.hakaton.dto.ErrorMessageDto;

public interface ErrorService {

  ErrorMessageDto getMessage();

  void log(String message);
}
