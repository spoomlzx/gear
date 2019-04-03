package com.spoom.gear.config;

import com.spoom.gear.config.security.*;
import com.spoom.gear.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * package com.spoom.gear.config
 *
 * @author spoomlan
 * @date 2019-03-28
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;
  @Autowired
  private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .headers()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new MyAccessDeniedHandler())
        // 这里是登录认证的时候authentication出错的处理
        .authenticationEntryPoint(myAuthenticationEntryPoint)
        .and().authorizeRequests()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/sys**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and().formLogin()
        .loginProcessingUrl("/user/login").permitAll()
        .successHandler(new MyAuthenticationSuccessHandler())
        .failureHandler(new MyAuthenticationFailureHandler())
        .and()
        .logout().logoutUrl("/logout").permitAll()
        .and()
        .addFilterBefore(bearerAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(new BearerAuthenticationProvider());
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public BearerAuthenticationFilter bearerAuthenticationFilter() {
    return new BearerAuthenticationFilter(authenticationManager, myAuthenticationEntryPoint);
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder());
    return provider;
  }
}
