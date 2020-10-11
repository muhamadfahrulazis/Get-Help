package com.jasa.Get.Help.controller;

import com.jasa.Get.Help.dto.UserRegistrationDto;
import com.jasa.Get.Help.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping
    public String registrationAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto){
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }

}
