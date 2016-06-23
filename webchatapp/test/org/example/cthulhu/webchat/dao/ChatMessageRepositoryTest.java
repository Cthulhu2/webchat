package org.example.cthulhu.webchat.dao;

import java.util.Date;
import java.util.List;
import org.example.cthulhu.webchat.entities.ChatMessage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.example.cthulhu.webchat.dao.ChatMessageRepository.HISTORY_LIMIT;
import static org.example.cthulhu.webchat.dao.ChatMessageRepository.TO_FETCH_LIMIT;
/**
 *
 * @author Cthulhu
 */
public class ChatMessageRepositoryTest {
    
    public ChatMessageRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEmpty() {
        System.out.println("empty");
        ChatMessageRepository instance = new ChatMessageRepository();
        assertEquals(0, instance.findAll().size());        
    }
    
    /**
     * Test of add method, of class MessageRepository.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        ChatMessage message = new ChatMessage("user", new Date(), "messageText");
        instance.add(message);
        assertEquals(message, instance.findAll().get(0));
        //
        instance.add(message);
        assertEquals(1, instance.findAll().size());
    }
    
    @Test
    public void testAddHistoryLimit() {
        System.out.println("add history limit");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        for(int i = 0; i < HISTORY_LIMIT; i++) {
            instance.add(new ChatMessage("user" + i, new Date(), "text"));
        }
        instance.add(new ChatMessage("user", new Date(), "one more"));
        //
        ChatMessage message = instance.findById(0);
        assertNull(message);
    }
    
    @Test
    public void testAddSame() {
        System.out.println("add same");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        Date timestamp = new Date();
        instance.add(new ChatMessage("user", timestamp, "messageText"));
        instance.add(new ChatMessage("user", timestamp, "messageText"));
        assertEquals(1, instance.findAll().size());
    }
    
    @Test
    public void testAddNullable() {
        System.out.println("add nullable");
        ChatMessageRepository instance = new ChatMessageRepository();
        try {
            instance.add(null);
            fail("null passed");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    /**
     * Test of remove method, of class MessageRepository.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        ChatMessage message0 = new ChatMessage("user0", new Date(), "messageText");
        ChatMessage message1 = new ChatMessage("user1", new Date(), "messageText");
        ChatMessage message2 = new ChatMessage("user2", new Date(), "messageText");
        //
        instance.add(message0);
        instance.remove(message0);
        assertEquals(0, instance.findAll().size());
        //
        instance.add(message1);
        instance.add(message2);
        instance.remove(message1);
        assertEquals(message2, instance.findAll().get(0));
    }
    
    @Test
    public void testRemoveHistoryLimit() {
        System.out.println("remove history limit");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        for(int i = 0; i < HISTORY_LIMIT + 100; i++) {
            instance.add(new ChatMessage("user" + i, new Date(), "text"));
        }
        //
        ChatMessage message = instance.findById(HISTORY_LIMIT - 1);
        instance.remove(message);
        message = instance.findById(HISTORY_LIMIT - 1);
        assertNull(message);
    }
    
    @Test
    public void testRemoveNullable() {
        System.out.println("remove nullable");
        ChatMessageRepository instance = new ChatMessageRepository();
        try {
            instance.remove(null);
            fail("null passed");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    /**
     * Test of findAll method, of class MessageRepository.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        for(int i = 0; i < TO_FETCH_LIMIT; i++) {
            instance.add(new ChatMessage("user" + i, new Date(), "messageText"));
        }
        //
        ChatMessage message = new ChatMessage("user", new Date(), "messageText");
        instance.add(message);
        List<ChatMessage> result = instance.findAll();
        assertEquals(TO_FETCH_LIMIT, result.size());
        assertEquals(message, result.get(TO_FETCH_LIMIT - 1)); // last add
    }
    
    @Test
    public void testFindById() {
        System.out.println("findById");
        ChatMessageRepository instance = new ChatMessageRepository();
        //
        // sequenceId also from 0, so
        for(int i = 0; i < 10; i++) {
            instance.add(new ChatMessage("user" + i, new Date(), "messageText"));
        }
        //
        ChatMessage message = instance.findById(8);
        assertNotNull(message);
        assertEquals("user8", message.getUserName());
    }
}
