package org.example.cthulhu.webchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.example.cthulhu.webchat.AuthProvider.ROLE_USER;

/**
 *
 * @author Cthulhu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        
        auth.inMemoryAuthentication();
    }
     
    
    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
       
      http.authorizeRequests()
        .antMatchers("/", "/login").permitAll()
        .antMatchers("/chatroom/**").access("hasRole('" + ROLE_USER + "')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("username").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/login");
    }
}
