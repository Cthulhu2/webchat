package org.example.cthulhu.webchat.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.cthulhu.webchat.entities.ChatMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cthulhu
 */
@Repository
@Scope("singleton")
public class ChatMessageRepository {

    public static final int TO_FETCH_LIMIT = 20;
    public static final int HISTORY_LIMIT = 1000;
    private int idSequence = 0;
    
    private final List<ChatMessage> messageList = new ArrayList<>(HISTORY_LIMIT);
    
    public synchronized ChatMessage add(ChatMessage message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (!messageList.contains(message)) {
            message.setId(idSequence++);
            messageList.add(message);
            if (messageList.size() >= HISTORY_LIMIT) {
                messageList.subList(0, messageList.size() - HISTORY_LIMIT).clear();
            }
            if (idSequence == Integer.MAX_VALUE - 1) {
                idSequence = 0;
            }
        }
        return message;
    }
    
    public synchronized void remove(ChatMessage message) {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (messageList.contains(message)) {
            messageList.remove(message);
        }
    }
    
    public synchronized List<ChatMessage> findAll() {
        int count = Math.min(messageList.size(), TO_FETCH_LIMIT);
        return Collections.unmodifiableList(messageList.subList(
                messageList.size() - count,
                messageList.size()));
    }
    
    public synchronized ChatMessage findById(int id) {
        for(ChatMessage message : messageList) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }
}
