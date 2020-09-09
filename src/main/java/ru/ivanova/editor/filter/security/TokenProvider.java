package ru.ivanova.editor.filter.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static org.springframework.util.StringUtils.hasText;

@Component
public class TokenProvider {

  private static final String TOKEN_HEADER = "Authorization";
  private static final String TOKEN_BEARER = "Bearer ";

  @Value("${security.token.key}")
  private String key;

  public boolean check(HttpServletRequest aRequest) {
    return validate(resolve(aRequest));
  }

  private boolean validate(String aToken) {
    return aToken.equals(key);
  }

  private String resolve(HttpServletRequest aRequest) {
    String bearer = aRequest.getHeader(TOKEN_HEADER);
    if (hasText(bearer) && bearer.startsWith(TOKEN_BEARER)) {
      return bearer.substring(TOKEN_BEARER.length());
    }
    return "";
  }
}
