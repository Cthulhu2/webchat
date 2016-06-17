package org.example.cthulhu.webchat;


import org.example.cthulhu.webchat.entities.User;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.MessageSourceAware;

import static org.example.cthulhu.webchat.AuthProvider.AUTH_USER;
import static org.example.cthulhu.webchat.util.Messages.getMessage;

/*
 *
 * @author Cthulhu
 */
@Controller
public class LoginController
        implements MessageSourceAware {

    private MessageSource ms;
    
    @Override
    public void setMessageSource(MessageSource ms) {
        this.ms = ms;
    }
    
    @RequestMapping(value={"/"})
    public String root() {
        return "redirect:/login";
    }
    
    @RequestMapping(value={"login"})
    public String login(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isUser = auth.getAuthorities().contains(AUTH_USER);
        //
        String name = isUser 
                ? ((User) auth.getPrincipal()).getUserName()
                : getMessage(ms, "login.guest");
        map.addAttribute("login_greetings",
                getMessage(ms, "login.lbl.greetings.fmt", name));
        //
        return "login";
    }
}
