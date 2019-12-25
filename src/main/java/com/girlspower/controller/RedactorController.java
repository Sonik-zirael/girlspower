package com.girlspower.controller;

import com.girlspower.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RedactorController {
    private final UserInfoService userInfoService;

    public RedactorController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/redactor")
    public String showRedactor() {
        return "redactor";
    }

    @PostMapping("/redactor")
    public String updateInfo(@RequestParam String name, @RequestParam String surname, @RequestParam String oldPassword,
                             @RequestParam String newPassword, @RequestParam Float aim,
                             Map<String, Object> model) {
        if (userInfoService.updateUserInfo(name, surname, oldPassword, newPassword, aim)) {
            model.put("result", "Success!");
        } else {
            model.put("result", "Something wrong");
        }
        return "redirect:/redactor";
    }
}
