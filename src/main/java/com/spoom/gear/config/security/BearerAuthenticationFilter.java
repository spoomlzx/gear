package com.spoom.gear.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * package com.spoom.gear.config.security
 *
 * @author spoomlan
 * @date 2019-03-29
 */

public class BearerAuthenticationFilter extends OncePerRequestFilter {

  @Value("${jwt.header}")
  private String tokenHeader;
  @Value("${jwt.tokenBegin")
  private String tokenBegin;
  private AuthenticationEntryPoint authenticationEntryPoint;
  private AuthenticationManager authenticationManager;

  /**
   * @param authenticationManager 注入默认的manager
   * @param authenticationEntryPoint 这里是authorization的时候出现token错误时的错误处理
   */
  public BearerAuthenticationFilter(AuthenticationManager authenticationManager,
      AuthenticationEntryPoint authenticationEntryPoint) {
    this.authenticationManager = authenticationManager;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    final boolean debug = this.logger.isDebugEnabled();
    String header = request.getHeader(tokenHeader);
    //如果request中没有token，跳过此filter
    if (header == null || !header.startsWith(tokenBegin)) {
      chain.doFilter(request, response);
      return;
    }

    try {
      String token = header.substring(tokenBegin.length());
      if (StringUtils.isBlank(token)) {
        throw new InsufficientAuthenticationException("token is empty");
      }
      if (debug) {
        this.logger
            .debug("Bearer Authentication Authorization header found : '" + token + "'");
      }
      BearerAuthenticationToken authRequest = new BearerAuthenticationToken(token);
      // 调用BearerAuthenticationProvider中的authenticate方法
      Authentication authResult = this.authenticationManager.authenticate(authRequest);

      if (debug) {
        this.logger.debug("Authentication success: " + authResult);
      }
      SecurityContextHolder.getContext().setAuthentication(authResult);
    } catch (AuthenticationException failed) {
      SecurityContextHolder.clearContext();
      this.authenticationEntryPoint.commence(request, response, failed);
      return;
    }

    chain.doFilter(request, response);
  }
}
