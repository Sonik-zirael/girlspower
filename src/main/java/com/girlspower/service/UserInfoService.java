package com.girlspower.service;

import com.girlspower.domain.User;
import com.girlspower.repos.UserRepository;
import com.girlspower.repos.UserRepositoryCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserRepositoryCustom {
    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }
}
