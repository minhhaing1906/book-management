package com.example.book.controller;

import com.example.book.entities.UserAccount;
import com.example.book.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    private final UserAccountService accountService;
    private final BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public SecurityController(UserAccountService accountService, BCryptPasswordEncoder bCryptEncoder) {
        this.accountService = accountService;
        this.bCryptEncoder = bCryptEncoder;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@ModelAttribute("userAccount") UserAccount user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        accountService.save(user);

        return "redirect:/login";
    }
}
