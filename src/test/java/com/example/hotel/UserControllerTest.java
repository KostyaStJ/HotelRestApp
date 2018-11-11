package com.example.hotel;


import com.example.hotel.controllers.UsersController;
import com.example.hotel.entities.User;
import com.example.hotel.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)

public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void getAllUsersTest () throws Exception {


        User test = new User();
        List<User> allUsers = Arrays.asList(test);
        given(service.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("users/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
