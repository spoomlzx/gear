package com.spoom.gear.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new User("spoom", "$2a$10$Fgi8bz6n0dE9pKjyq.ysN.L0j4ZxMYpGmJGi0m6CVUv/A83fH439W", true,
        true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
  }
}
