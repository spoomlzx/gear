package com.spoom.gear.config.security;

import com.spoom.gear.utils.CommonResult;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * package com.spoom.gear.config
 *
 * @author spoomlan
 * @date 2019-03-28
 */

public class MyAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    response.getWriter().print(new CommonResult().unauthorized(accessDeniedException.getMessage()));
    response.getWriter().flush();
  }
}
