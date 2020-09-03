package ru.ivanova.editor.service;

import ru.ivanova.editor.model.ErrorMessage;

public interface ErrorService {

  ErrorMessage getMessage();

  void addMessage(String message);

}
