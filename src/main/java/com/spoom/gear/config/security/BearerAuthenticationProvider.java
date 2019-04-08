package com.spoom.gear.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoom.gear.model.User;
import com.spoom.gear.service.UserService;
import com.spoom.gear.service.impl.UserDetailsServiceImpl;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * package com.spoom.gear.config.security 对{@link BearerAuthenticationToken}进行认证
 *
 * @author spoomlan
 * @date 2019-03-30
 */
@Component
public class BearerAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;
  @Resource
  private JwtUtils jwtUtils;
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  /**
   * @param authentication {@link BearerAuthenticationToken}
   * @return {@link UsernamePasswordAuthenticationToken}
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = (String) authentication.getPrincipal();
    String username = jwtUtils.getUsernameFromToken(token);
    if (username == null) {
      throw new BadCredentialsException("token invalid");
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (!jwtUtils.isTokenExpired(token)) {
      return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
          userDetails.getPassword(), userDetails.getAuthorities());
    } else {
      throw new CredentialsExpiredException("token expired");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return BearerAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
