package ru.ivanova.editor.controller;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController implements ApplicationContextAware {

  private org.springframework.context.ApplicationContext context;

  @PostMapping("/exit")
  public void shutdownContext() {
    ((ConfigurableApplicationContext) context).close();
  }

  @PostMapping("/exit-success")
  public String exitSuccess() {
    return "exit-success";
  }

  @Override
  public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
    this.context = applicationContext;
  }
}