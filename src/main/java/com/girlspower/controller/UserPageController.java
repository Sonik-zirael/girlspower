package com.girlspower.controller;

import com.girlspower.domain.User;
import com.girlspower.repos.UserInfoRepository;
import com.girlspower.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserPageController {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;

    public UserPageController(UserInfoRepository userInfoRepository, UserInfoService userInfoService) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/cabinet")
    public String showUserCabinet(Map<String, Object> model) {
        User user = userInfoService.findByAuthentication();
        model.put("username", user.getInfo().getFirstName());
        model.put("info", user.getInfo());
        return "/cabinet";
    }
}
