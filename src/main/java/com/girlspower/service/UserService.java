package com.girlspower.service;

import com.girlspower.domain.Role;
import com.girlspower.domain.User;
import com.girlspower.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String username, String name, String surname, String birthday,
                          float height, float weight, String password) {
        if (userRepository.findByUsername(username) != null)
            return "User exists!";
        User newUser = new User(username, name, surname, birthday, height, weight, password);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        return "";
    }
}
