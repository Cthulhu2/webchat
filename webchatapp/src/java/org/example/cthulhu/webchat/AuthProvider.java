package org.example.cthulhu.webchat;

import java.util.Collections;
import org.example.cthulhu.webchat.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cthulhu
 */
@Component(value = "authProvider")
public class AuthProvider
        implements AuthenticationProvider {

    public static final String ROLE_USER = "ROLE_USER";

    public static final GrantedAuthority AUTH_USER = new GrantedAuthority() {

        @Override
        public String getAuthority() {
            return ROLE_USER;
        }
    };

    @Autowired
    private UserRepository userProfileService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        Object principal = authentication.getPrincipal();

        if (principal == null || principal.toString().trim().isEmpty()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(
                        principal.toString().trim(),
                        null,
                        Collections.singleton(AUTH_USER));

        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
