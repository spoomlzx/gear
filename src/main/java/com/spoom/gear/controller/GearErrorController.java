package com.spoom.gear.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package com.spoom.gear.controller
 *
 * @author spoomlan
 * @date 2019-03-28
 */
@RestController
public class GearErrorController implements ErrorController {

  @RequestMapping("/error")
  public void handleError(HttpServletRequest request, HttpServletResponse response) {
    //获取statusCode:401,404,500
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    try {
      response.getWriter().print("{\"code\":500,\"message\":\"error\"}");
      response.getWriter().flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getErrorPath() {
    return "error";
  }
}
