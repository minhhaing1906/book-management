package com.example.book.controller;

import com.example.book.entities.UserAccount;
import com.example.book.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@ModelAttribute("userAccount") UserAccount user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        userAccountRepository.save(user);

        return "redirect:/login";
    }
}
