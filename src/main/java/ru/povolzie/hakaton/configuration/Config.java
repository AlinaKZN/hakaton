package ru.povolzie.hakaton.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.AllArgsConstructor;
import ru.povolzie.hakaton.filter.email.ValidationFilter;
import ru.povolzie.hakaton.filter.security.TokenProvider;
import ru.povolzie.hakaton.service.GeoDataService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@AllArgsConstructor
@Configuration
public class Config {

  GeoDataService geoDataService;

  TokenProvider tokenProvider;

  @Bean
  public FilterRegistrationBean<ValidationFilter> emailValidatorFilterRegistrationBean() {
    FilterRegistrationBean<ValidationFilter> registrationBean
        = new FilterRegistrationBean<>();
    registrationBean.setFilter(new ValidationFilter(geoDataService));
    registrationBean.addUrlPatterns(ValidationFilter.GEODATA_SET_URL);
    registrationBean.setOrder(2);
    return registrationBean;
  }

 /* @Bean
  public FilterRegistrationBean<SecurityFilter> securityFilterRegistrationBean() {
    FilterRegistrationBean<SecurityFilter> registrationBean
        = new FilterRegistrationBean<>();
    registrationBean.setFilter(new SecurityFilter(tokenProvider));
    registrationBean.addUrlPatterns(SecurityFilter.ERROR_URLS, SecurityFilter.PROFILE_URLS);
    registrationBean.setOrder(1);
    return registrationBean;
  }*/

  @Configuration
  @EnableSwagger2
  public class SwaggerConfig {
    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
    }
  }
}
