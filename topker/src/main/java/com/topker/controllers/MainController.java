package com.topker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/aboutUs")
    public String getAboutUsPage(Model model){

        model.addAttribute("view", "about-us");

        return "base-layout";
    }

    @GetMapping("/partners")
    public String getPartnersPage(Model model){

        model.addAttribute("view", "partners");

        return "base-layout";
    }
}
