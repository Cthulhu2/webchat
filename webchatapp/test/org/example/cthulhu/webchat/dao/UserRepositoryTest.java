package org.example.cthulhu.webchat.dao;

import org.example.cthulhu.webchat.entities.User;
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
public class UserRepositoryTest {
    
    public UserRepositoryTest() {
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
        UserRepository instance = new UserRepository();
        assertEquals(0, instance.findAll().size());
    }
    
    /**
     * Test of add method, of class UserRepository.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        User user = new User("qwerty");
        UserRepository instance = new UserRepository();
        instance.add(user);
        assertEquals(user, instance.findAll().get(0));
    }
    
    @Test
    public void testAddSame() {
        System.out.println("add same");
        UserRepository instance = new UserRepository();
        instance.add(new User("qwerty"));
        instance.add(new User("qwerty"));
        assertEquals(1, instance.findAll().size());
    }
    
    @Test
    public void testAddNullable() {
        System.out.println("add nullable");
        UserRepository instance = new UserRepository();
        try {
            instance.add(null);
            fail("null passed");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    /**
     * Test of remove method, of class UserRepository.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        UserRepository instance = new UserRepository();
        //
        User user0 = new User("user0");
        User user1 = new User("user1");
        User user2 = new User("user2");
        //
        instance.add(user0);
        instance.remove(user0);
        assertEquals(0, instance.findAll().size());
        //
        instance.add(user1);
        instance.add(user2);
        instance.remove(user1);
        assertEquals(user2, instance.findAll().get(0));
    }
    
    @Test
    public void testRemoveNullable() {
        System.out.println("remove nullable");
        UserRepository instance = new UserRepository();
        try {
            instance.remove(null);
            fail("null passed");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    /**
     * Test of findBy method, of class UserRepository.
     */
    @Test
    public void testFindByName() {
        System.out.println("findBy");
        UserRepository instance = new UserRepository();
        User user = new User("qwerty");
        instance.add(user);
        User result = instance.findByName(user.getUserName());
        assertEquals(user, result);
    }
    
    @Test
    public void testFindByNameNonExisting() {
        System.out.println("findBy nonexisting");
        UserRepository instance = new UserRepository();
        assertEquals(null, instance.findByName("nonexistingusername"));
    }

    /**
     * Test of findAll method, of class UserRepository.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserRepository instance = new UserRepository();
        User[] expResult = new User[] {
                new User("user0"), new User("user1"), new User("user2")
        };
        for(User user : expResult) {
            instance.add(user);
        }
        //
        assertArrayEquals(expResult, instance.findAll().toArray());
    }    
}
