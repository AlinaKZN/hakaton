package ru.povolzie.hakaton.filter.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ru.povolzie.hakaton.dto.GeoDataDto;
import ru.povolzie.hakaton.message.Messages;
import ru.povolzie.hakaton.service.GeoDataService;
import ru.povolzie.hakaton.util.EmailValidator;

@AllArgsConstructor
public class ValidationFilter implements Filter {

  public static final String GEODATA_SET_URL = "/profiles/set";
  public static final String ERROR_MESSAGE_KEY = "msg";

  GeoDataService geoDataService;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    ServletRequest requestWrapper = new GeoDataRequestWrapper(request);
    GeoDataDto profile = new ObjectMapper().readValue(requestWrapper.getInputStream(), GeoDataDto.class);
    if (profile != null) {
      String email = profile.getEmail();
      if (!EmailValidator.isValid(email)) {
        ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        servletResponse.getOutputStream().write(restResponseBytes(Map.of(ERROR_MESSAGE_KEY, Messages.INCORRECT_EMAIL)));
        return;
      }
     /* if (geoDataService.findByEmail(email).isPresent()) {
        errorService.log(Messages.DUPLICATE_EMAIL);
        ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
        servletResponse.getOutputStream().write(restResponseBytes(Map.of(ERROR_MESSAGE_KEY, Messages.DUPLICATE_EMAIL)));
        return;
      }*/
    }

    filterChain.doFilter(requestWrapper, servletResponse);
  }

  private byte[] restResponseBytes(Map map) throws IOException {
    String serialized = new ObjectMapper().writeValueAsString(map);
    return serialized.getBytes();
  }
}
