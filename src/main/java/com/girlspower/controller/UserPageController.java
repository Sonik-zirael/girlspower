package com.girlspower.controller;

import com.girlspower.domain.User;
import com.girlspower.service.TipsService;
import com.girlspower.service.UserInfoService;
import com.girlspower.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class UserPageController {
    private final UserInfoService userInfoService;
    private final TipsService tipsService;
    private final StatisticsService statisticsService;

    public UserPageController(UserInfoService userInfoService, TipsService tipsService,
                              StatisticsService statisticsService) {
        this.userInfoService = userInfoService;
        this.tipsService = tipsService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/cabinet")
    public String showUserCabinet(Map<String, Object> model) {
        User user = userInfoService.findByAuthentication();
        model.put("username", user.getInfo().getFirstName());
        model.put("info", userInfoService.getUserInfo(user));
        model.put("dayTip", tipsService.getTodayAdvice());
        double BMI = user.getInfo().getWeight() / Math.pow(user.getInfo().getHeight(), 2);
        model.put("BMI", (double) Math.round(BMI * 100) / 100);
        String descr;
        if (BMI < 16) {
            descr = "дефицит массы тела";
        } else if (BMI < 18.5) {
            descr = "недостаточная масса тела";
        } else if (BMI < 25) {
            descr = "ура, нормальная масса тела";
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
        String statisticsString = "Ваш индекс лучше, чем у " + statisticsService.getWorstUsers() + " пользователей";
        model.put("stat", statisticsString);
        if (user.getInfo().getAim() != null) {
            Float diff = user.getInfo().getWeight() - user.getInfo().getAim();
            String aimString;
            if (Math.abs(diff) < 0.1) {
                aimString = "Вы достигли своей цели! Теперь старайтесь поддерживать свой вес.";
            } else {
                aimString = "До вашей цели осталось " +
                        (diff < 0 ? "набрать " : "сбросить ") +
                        Math.abs(diff) + " кг";
            }
            model.put("aim", aimString);
        }
        return "cabinet";
    }

    @PostMapping("/cabinetPost")
    public String userSave(@RequestParam Float weight,
                           @RequestParam Float height) {
        User user = userInfoService.findByAuthentication();
        if (height > 100) {
            height /= 100;
        }
        userInfoService.updateUserParams(weight, height);
        Date date = new Date();
        statisticsService.updateStatistics(user, weight, height, date);
        return "redirect:/cabinet";
    }
}
