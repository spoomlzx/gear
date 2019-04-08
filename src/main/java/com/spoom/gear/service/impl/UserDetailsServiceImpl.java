package com.spoom.gear.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spoom.gear.model.User;
import com.spoom.gear.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * package com.spoom.gear.service
 *
 * @author spoomlan
 * @date 2019-03-28
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isBlank(username)) {
      throw new BadCredentialsException("username is empty");
    }
    User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
    if (null == user) {
      throw new BadCredentialsException("username not exist");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
        true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
  }
}
