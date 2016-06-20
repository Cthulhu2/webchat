package org.example.cthulhu.webchat.config;

import static org.example.cthulhu.webchat.AuthProvider.AUTH_USER;

import org.example.cthulhu.webchat.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Cthulhu
 */
@Configuration
@EnableWebSecurity
@ComponentScan("org.example.cthulhu.webchat")
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web)
            throws Exception {

        web.ignoring().antMatchers("/res/**");
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/chatroom/**").hasAuthority(AUTH_USER.getAuthority());
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("ssoId").passwordParameter("password")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/chatroom");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
        http.csrf()
                .csrfTokenRepository(csrfTokenRepository());
        http.exceptionHandling()
                .accessDeniedPage("/login?error");
        http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
