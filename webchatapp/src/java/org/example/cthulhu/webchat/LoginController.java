package org.example.cthulhu.webchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cthulhu
 */
@Controller
public class LoginController {

    @RequestMapping(value={"/"})
    public String root() {
        return "redirect:/login";
    }
    
    @RequestMapping(value={"login"})
    public String login() {
        return "login";
    }
}
