package com.girlspower.service;

import com.girlspower.domain.User;
import com.girlspower.domain.UserInfo;
import com.girlspower.repos.UserInfoRepository;
import com.girlspower.repos.UserRepository;
import com.girlspower.repos.UserRepositoryCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserRepositoryCustom {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public User findByAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    public boolean updateUserInfo(String name, String surname, String oldPassword, String newPassword) {
        try {
            User currentUser = findByAuthentication();
            UserInfo currentUserInfo = userInfoRepository.findByUser(currentUser);
            if (!name.equals("")) {
                currentUserInfo.setFirstName(name);
                currentUser.getInfo().setFirstName(name);
            }
            if (!surname.equals("")) {
                currentUserInfo.setLastName(surname);
                currentUser.getInfo().setLastName(surname);
            }
            if (!oldPassword.equals("") && !newPassword.equals("") && oldPassword.equals(currentUser.getPassword())) {
                currentUser.setPassword(newPassword);
            }
            userInfoRepository.save(currentUserInfo);
            userRepository.save(currentUser);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateUserParams(Float weight, Float height) {
        try {
            User currentUser = findByAuthentication();
            UserInfo currentUserInfo = userInfoRepository.findByUser(currentUser);
            if (weight != 0) {
                currentUserInfo.setWeight(weight);
                currentUser.getInfo().setWeight(weight);
            }
            if (height != 0) {
                currentUserInfo.setHeight(height);
                currentUser.getInfo().setHeight(height);
            }
            userInfoRepository.save(currentUserInfo);
            userRepository.save(currentUser);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
