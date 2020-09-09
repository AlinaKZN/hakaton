package ru.ivanova.editor.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.AllArgsConstructor;
import ru.ivanova.editor.filter.email.EmailValidationFilter;
import ru.ivanova.editor.filter.security.SecurityFilter;
import ru.ivanova.editor.filter.security.TokenProvider;
import ru.ivanova.editor.service.ErrorService;
import ru.ivanova.editor.service.ProfileService;

@AllArgsConstructor
@Configuration
public class Config {

  ProfileService profileService;

  ErrorService errorService;

  TokenProvider tokenProvider;

  @Bean
  public FilterRegistrationBean<EmailValidationFilter> emailValidatorFilterRegistrationBean() {
    FilterRegistrationBean<EmailValidationFilter> registrationBean
        = new FilterRegistrationBean<>();
    registrationBean.setFilter(new EmailValidationFilter(profileService, errorService));
    registrationBean.addUrlPatterns(EmailValidationFilter.PROFILE_SET_URL);
    registrationBean.setOrder(2);
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<SecurityFilter> securityFilterRegistrationBean() {
    FilterRegistrationBean<SecurityFilter> registrationBean
        = new FilterRegistrationBean<>();
    registrationBean.setFilter(new SecurityFilter(tokenProvider));
    registrationBean.addUrlPatterns(SecurityFilter.ERROR_URLS, SecurityFilter.PROFILE_URLS);
    registrationBean.setOrder(1);
    return registrationBean;
  }
}
