package org.example.cthulhu.webchat.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.cthulhu.webchat.entities.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cthulhu
 */
@Repository
@Scope("singleton")
public class UserRepository {
    
    private final List<User> userList = new ArrayList<>();
    
    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (!userList.contains(user)) {
            userList.add(user);
        }
    }

    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (userList.contains(user)) {
            userList.remove(user);
        }
    }
    
    public User findByName(String name) {
        for(User user : userList) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        return null;
    }
    
    public List<User> findAll() {
        return Collections.unmodifiableList(userList);
    }
}
