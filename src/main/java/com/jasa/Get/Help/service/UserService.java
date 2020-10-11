package com.jasa.Get.Help.service;

import com.jasa.Get.Help.dto.UserRegistrationDto;
import com.jasa.Get.Help.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
