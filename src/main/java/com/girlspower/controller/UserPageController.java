package com.girlspower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserPageController {
    @GetMapping("/cabinet")
    public String showUserCabinet(Map<String, Object> model) {
        model.put("username", "Hello user");
        return "/cabinet";
    }
}
