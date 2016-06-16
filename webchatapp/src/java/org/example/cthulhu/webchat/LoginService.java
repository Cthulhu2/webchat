package org.example.cthulhu.webchat;

import org.example.cthulhu.webchat.dao.UserRepository;
import org.example.cthulhu.webchat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cthulhu
 */
@Service
public class LoginService {
    
    @Autowired
    UserRepository users;
    
    
    public void doLogin(String username) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, null));
        users.add(new User(username));
    }
}
