package com.spoom.gear.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * package com.spoom.gear.config
 *
 * @author spoomlan
 * @date 2019-03-28
 */

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

  	//todo 如果认证成功，生成jwt token并返回
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    response.getWriter().print("{\"code\":200,\"message\":\"登录成功\"}");
    response.getWriter().flush();
  }
}
