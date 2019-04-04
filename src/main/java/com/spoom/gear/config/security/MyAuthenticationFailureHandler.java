package com.spoom.gear.config.security;

import com.spoom.gear.utils.CommonResult;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * package com.spoom.gear.config
 * authentication failure
 *
 * @author spoomlan
 * @date 2019-03-28
 */

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    response.getWriter().print(new CommonResult().unauthenticated(exception.getMessage()));
    response.getWriter().flush();
  }
}
