package ru.ivanova.editor.filter.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ru.ivanova.editor.dto.ProfileDto;
import ru.ivanova.editor.message.Messages;
import ru.ivanova.editor.service.ErrorService;
import ru.ivanova.editor.service.ProfileService;
import ru.ivanova.editor.util.EmailValidator;

@AllArgsConstructor
public class EmailValidationFilter implements Filter {

  public static final String PROFILE_SET_URL = "/profiles/set";

  ProfileService profileService;

  ErrorService errorService;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    ServletRequest requestWrapper = new ProfileRequestWrapper(request);
    ProfileDto profile = new ObjectMapper().readValue(requestWrapper.getInputStream(), ProfileDto.class);
    if (profile != null) {
      String email = profile.getEmail();
      if (!EmailValidator.isValid(email)) {
        errorService.addMessage(Messages.INCORRECT_EMAIL);
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST, Messages.INCORRECT_EMAIL);
        return;
      }
      if (profileService.findByEmail(email).isPresent()) {
        errorService.addMessage(Messages.DUPLICATE_EMAIL);
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN, Messages.DUPLICATE_EMAIL);
        return;
      }
    }
    filterChain.doFilter(requestWrapper, servletResponse);
  }
}
