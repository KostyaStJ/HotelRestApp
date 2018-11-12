package com.example.hotel.services;

import com.example.hotel.entities.User;
import com.example.hotel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createNewUser(String login, String name, String lastname) {
        User one = new User();
        one.setFirstName(name);
        one.setLastName(lastname);
        one.setLogin(login);

        userRepository.save(one);
        return one;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


}
