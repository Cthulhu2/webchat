package org.example.cthulhu.webchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cthulhu
 */
@Controller
public class ChatRoomController {
    
    @RequestMapping(value={"/chatroom"})
    public String chatRoom() {
        return "chatroom";
    }    
}
