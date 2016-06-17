package org.example.cthulhu.webchat;

import java.util.Collections;
import org.example.cthulhu.webchat.dao.UserRepository;
import org.example.cthulhu.webchat.entities.User;
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
@Component
public class AuthProvider
        implements AuthenticationProvider {

    public static final GrantedAuthority AUTH_USER = new GrantedAuthority() {

        @Override
        public String getAuthority() {
            return "USER";
        }
    };

    @Autowired
    private UserRepository users;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();

        if (username == null || username.trim().isEmpty()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        User user = new User(username.trim());
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(
                        user, null, Collections.singleton(AUTH_USER));
        users.add(user);
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
