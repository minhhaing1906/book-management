package com.example.book.controller;

import com.example.book.entities.UserAccount;
import com.example.book.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SecurityController {

    private final UserAccountService accountService;
    private final BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public SecurityController(UserAccountService accountService, BCryptPasswordEncoder bCryptEncoder) {
        this.accountService = accountService;
        this.bCryptEncoder = bCryptEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid @ModelAttribute("userAccount") UserAccount user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (accountService.checkUserName(user.getUserName()))
            bindingResult.addError(new FieldError("user", "userName", "username already exists, please choose another name"));

        if(accountService.checkEmail(user.getEmail()))
            bindingResult.addError(new FieldError("user", "email", "Email address already in use"));

        if (bindingResult.hasErrors()) {
            System.out.println("-----------------Hit error------------------");
            return "security/register";
        }

        redirectAttributes.addFlashAttribute("message", "Success! Your registration is complete");

        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        accountService.save(user);

        return "redirect:/login";
    }
}
