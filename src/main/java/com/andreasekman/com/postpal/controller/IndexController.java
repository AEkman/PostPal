package com.andreasekman.com.postpal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("dateTime", new Date());
        model.addAttribute("username", "Andreas Ekman");
        model.addAttribute("mode", "development");

        return "index";
    }
}
