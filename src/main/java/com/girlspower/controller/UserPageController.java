package com.girlspower.controller;

import com.girlspower.domain.User;
import com.girlspower.service.TipsService;
import com.girlspower.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserPageController {
    private final UserInfoService userInfoService;
    private final TipsService tipsService;

    public UserPageController(UserInfoService userInfoService, TipsService tipsService) {
        this.userInfoService = userInfoService;
        this.tipsService = tipsService;
    }

    @GetMapping("/cabinet")
    public String showUserCabinet(Map<String, Object> model) {
        User user = userInfoService.findByAuthentication();
        model.put("username", user.getInfo().getFirstName());
        model.put("info", user.getInfo());
        model.put("dayTip", tipsService.getTodayAdvice());
        return "/cabinet";
    }
}
