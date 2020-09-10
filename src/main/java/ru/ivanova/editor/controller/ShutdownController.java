package ru.ivanova.editor.controller;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ShutdownController implements ApplicationContextAware {

  private org.springframework.context.ApplicationContext context;

  @GetMapping("/exit")
  public void shutdownContext() {
    log.info("приложение закрыто");
    ((ConfigurableApplicationContext) context).close();
  }

  @Override
  public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
    this.context = applicationContext;
  }
}