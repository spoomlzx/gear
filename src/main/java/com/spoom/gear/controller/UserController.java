package com.spoom.gear.controller;

import com.spoom.gear.model.User;
import com.spoom.gear.utils.CommonResult;
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
  public CommonResult getUserInfo() {
    return new CommonResult().success("spoom");
  }

  @GetMapping("/sys/info")
  public CommonResult getUserInfo2() {
    return new CommonResult().success(new User());
  }
}
