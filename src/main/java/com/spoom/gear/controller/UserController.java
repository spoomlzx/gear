package com.spoom.gear.controller;

import com.spoom.gear.utils.BaseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package com.spoom.gear.controller
 *
 * @author spoomlan
 * @date 2019-03-28
 */
@RestController
class UserController {

  @GetMapping("/user/info")
  public BaseResult getUserInfo() {
    return BaseResult.success().put("user", "spoom");
  }

  @GetMapping("/sys/info")
  public BaseResult getUserInfo2() {
    return BaseResult.success().put("user", "spoom");
  }
}
