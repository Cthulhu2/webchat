package org.example.cthulhu.webchat.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.cthulhu.webchat.entities.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cthulhu
 */
@Repository
@Scope("singleton")
public class MessageRepository {

    public static final int MESSAGE_LIMIT = 20;
    private int idSequence = 0;
    
    private final List<Message> messageList = new ArrayList<>(MESSAGE_LIMIT * 2);
    
    public synchronized void add(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (!messageList.contains(message)) {
            idSequence++;
            message.setId(idSequence);
            messageList.add(message);
            if (messageList.size() >= MESSAGE_LIMIT * 2) {
                messageList.subList(0, MESSAGE_LIMIT - 1).clear();
            }
        }
    }
    
    public synchronized void remove(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (messageList.contains(message)) {
            messageList.remove(message);
        }
    }
    
    public synchronized List<Message> findAll() {
        int count = Math.min(messageList.size(), MESSAGE_LIMIT);
        return Collections.unmodifiableList(messageList.subList(
                messageList.size() - count,
                messageList.size()));
    }
}
