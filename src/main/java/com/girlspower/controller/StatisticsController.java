package com.girlspower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    @GetMapping("/statistics")
    public String showStatistics() {
        return "statistics";
    }
}