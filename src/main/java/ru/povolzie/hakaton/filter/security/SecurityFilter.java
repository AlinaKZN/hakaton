package ru.povolzie.hakaton.filter.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ru.povolzie.hakaton.message.Messages;

@AllArgsConstructor
public class SecurityFilter implements Filter {

  public static final String PROFILE_URLS = "/profile/*";

  public static final String ERROR_URLS = "/error/*";

  private TokenProvider tokenProvider;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    if (tokenProvider.check(request)) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, Messages.BAD_TOKEN);
    }
  }
}
