package com.girlspower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedactorController {
    @GetMapping("/redactor")
    public String showRedactor() {
        return "redactor";
    }
}
