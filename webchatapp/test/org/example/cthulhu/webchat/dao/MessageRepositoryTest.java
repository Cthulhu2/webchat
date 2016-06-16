package org.example.cthulhu.webchat.dao;

import java.util.Date;
import java.util.List;
import org.example.cthulhu.webchat.entities.Message;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cthulhu
 */
public class MessageRepositoryTest {
    
    public MessageRepositoryTest() {
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
        MessageRepository instance = new MessageRepository();
        assertEquals(0, instance.findAll().size());        
    }
    
    /**
     * Test of add method, of class MessageRepository.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        MessageRepository instance = new MessageRepository();
        //
        Message message = new Message("user", new Date(), "messageText");
        instance.add(message);
        assertEquals(message, instance.findAll().get(0));
        //
        instance.add(message);
        assertEquals(1, instance.findAll().size());
    }
    
    @Test
    public void testAddSame() {
        System.out.println("add same");
        MessageRepository instance = new MessageRepository();
        //
        Date timestamp = new Date();
        instance.add(new Message("user", timestamp, "messageText"));
        instance.add(new Message("user", timestamp, "messageText"));
        assertEquals(1, instance.findAll().size());
    }
    
    @Test
    public void testAddNullable() {
        System.out.println("add nullable");
        MessageRepository instance = new MessageRepository();
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
        MessageRepository instance = new MessageRepository();
        //
        Message message0 = new Message("user0", new Date(), "messageText");
        Message message1 = new Message("user1", new Date(), "messageText");
        Message message2 = new Message("user2", new Date(), "messageText");
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
    public void testRemoveNullable() {
        System.out.println("remove nullable");
        MessageRepository instance = new MessageRepository();
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
        MessageRepository instance = new MessageRepository();
        //
        for(int i = 0; i < MessageRepository.MESSAGE_LIMIT; i++) {
            instance.add(new Message("user" + i, new Date(), "messageText"));
        }
        //
        Message message = new Message("user", new Date(), "messageText");
        instance.add(message);
        List<Message> result = instance.findAll();
        assertEquals(MessageRepository.MESSAGE_LIMIT, result.size());
        assertEquals(message, result.get(MessageRepository.MESSAGE_LIMIT - 1));
    }    
}
