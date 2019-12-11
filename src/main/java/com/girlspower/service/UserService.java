package com.girlspower.service;

import com.girlspower.domain.Role;
import com.girlspower.domain.User;
import com.girlspower.domain.UserInfo;
import com.girlspower.repos.UserInfoRepository;
import com.girlspower.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public String addUser(String username, String name, String surname, String birthday,
                          Float height, Float weight, String password) {
        if (userRepository.findByUsername(username) != null)
            return "User exists!";
        User newUser = new User(username, password);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date bDate = null;
        try {
            if(!birthday.isEmpty())
                bDate = formatter.parse(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = UserInfo.builder()
                .firstName(name)
                .lastName(surname)
                .birthday(bDate)
                .user(newUser)
                .height(height)
                .weight(weight).build();
        userInfoRepository.save(userInfo);
        return "";
    }
}
