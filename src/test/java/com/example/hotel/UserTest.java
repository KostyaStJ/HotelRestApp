package com.example.hotel;


import com.example.hotel.entities.User;
import com.example.hotel.repositories.UserRepository;
import com.example.hotel.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = HotelApplication.class)
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUserTest(){

        User newUser = userService.createNewUser("test", "name", "lastName");

        User userOrigin = new User("test", "name", "lastName");

        assertEquals(newUser, userOrigin);

    }

    @Before
    public void initUsers(){
        User one = new User("test1", "name1", "lastName1");
        User two = new User("test2", "name2", "lastName2");
        User three = new User("test3", "name3", "lastName3");
        userRepository.save(one);
        userRepository.save(two);
        userRepository.save(three);
    }

    @Test
    public void getAllUsersTest(){

        assertTrue("Test", userRepository.count() >= 3);
    }

    @After
    public void deleteUsers(){
        userRepository.deleteById("test1");
        userRepository.deleteById("test2");
        userRepository.deleteById("test3");

    }
}
