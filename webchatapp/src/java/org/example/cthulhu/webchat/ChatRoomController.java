package org.example.cthulhu.webchat;

import java.util.Date;
import java.util.List;
import org.example.cthulhu.webchat.dao.ChatMessageRepository;
import org.example.cthulhu.webchat.entities.ChatMessage;
import org.example.cthulhu.webchat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Cthulhu
 */
@Controller
public class ChatRoomController {

    @Autowired
    private ChatMessageRepository messages;
    @Autowired
    private SimpMessagingTemplate websocketMessaging;

    @RequestMapping(value = {"/chatroom"})
    public String chatRoom() {
        return "chatroom";
    }

    @ResponseBody
    @RequestMapping(value = {"/chatroom/messages"})
    public List<ChatMessage> getMessages() {
        return messages.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/chatroom/send", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String text) {
        String username = ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUserName();
        ChatMessage message = messages.add(new ChatMessage(username, new Date(), text));
        websocketMessaging.convertAndSend("/topic/chat", message);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/chatroom/remove", method = RequestMethod.POST)
    public void removeMessage(@RequestBody int id) {
        String username = ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUserName();

        ChatMessage mDel = messages.findById(id);
        if (mDel != null) {
            if (!username.equals(mDel.getUserName())) {
                throw new BadCredentialsException("Only owner can remove this message");
            }
            messages.remove(mDel);
            websocketMessaging.convertAndSend("/topic/remove", mDel.getId());
        }
    }
}
