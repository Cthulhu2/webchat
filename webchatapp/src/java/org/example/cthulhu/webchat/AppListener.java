package org.example.cthulhu.webchat;

import java.util.List;
import org.apache.log4j.Logger;
import org.example.cthulhu.webchat.entities.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cthulhu
 */
@Service
public class AppListener
        implements ApplicationListener {

    private static final Logger log = Logger.getLogger(AppListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent e) {
        if (e instanceof InteractiveAuthenticationSuccessEvent) {
            onAuthSuccess((InteractiveAuthenticationSuccessEvent) e);
        } else if (e instanceof SessionDestroyedEvent) {
            onSessionDestroyed((SessionDestroyedEvent)e);
        }
    }

    private void onAuthSuccess(InteractiveAuthenticationSuccessEvent e) {
        User user = ((User) e.getAuthentication().getPrincipal());
        log.info(String.format("%s is logged in.", user));
    }

    private void onSessionDestroyed(SessionDestroyedEvent e) {
        List<SecurityContext> lstSecurityContext = e.getSecurityContexts();
        User user;
        for (SecurityContext securityContext : lstSecurityContext) {
            user = (User) securityContext.getAuthentication().getPrincipal();
            log.info(String.format("%s is logged out.", user));
        }
    }
}
