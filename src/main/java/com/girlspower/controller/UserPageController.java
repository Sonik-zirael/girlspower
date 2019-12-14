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
        double BMI = user.getInfo().getWeight() / Math.pow(user.getInfo().getHeight(), 2);
        model.put("BMI", (double)Math.round(BMI * 100) / 100);
        String descr;
        if (BMI < 16) {
            descr = "дефицит массы тела";
        } else if (BMI < 18.5) {
            descr = "недостаточная масса тела";
        } else if (BMI < 25) {
            descr = "нормальная масса тела :) ";
        } else if (BMI < 30) {
            descr = "избыточная масса тела";
        } else if (BMI < 35) {
            descr = "ожирение первой степени";
        } else if (BMI < 40) {
            descr = "ожирение второй степени";
        } else {
            descr = "ожирение третьей степени";
        }
        model.put("descr", descr);
        return "/cabinet";
    }
}
