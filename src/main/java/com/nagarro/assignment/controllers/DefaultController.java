package com.nagarro.assignment.controllers;

import com.nagarro.assignment.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DefaultController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String defaultPage(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "statement";
    }
    @GetMapping("/error")
    public String error() {
        return "403";
    }

}
