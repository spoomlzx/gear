package com.spoom.gear.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * package com.spoom.gear.config.security 对{@link BearerAuthenticationToken}进行认证
 *
 * @author spoomlan
 * @date 2019-03-30
 */

public class BearerAuthenticationProvider implements AuthenticationProvider {

  /**
   * @param authentication {@link BearerAuthenticationToken}
   * @return {@link UsernamePasswordAuthenticationToken}
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = (String) authentication.getPrincipal();

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("username", "password");

    return auth;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return BearerAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
