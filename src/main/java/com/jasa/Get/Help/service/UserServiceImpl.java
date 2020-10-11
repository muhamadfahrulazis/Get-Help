package com.jasa.Get.Help.service;

import com.jasa.Get.Help.dto.UserRegistrationDto;
import com.jasa.Get.Help.model.Role;
import com.jasa.Get.Help.model.User;
import com.jasa.Get.Help.repository.UserRepository;
import com.jasa.Get.Help.util.AES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String eUsername = AES256.encrypt(username);
        User user = userRepository.findByEmail(eUsername);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        String dEmail = AES256.decrypt(user.getEmail());
        return new org.springframework.security.core.userdetails.User(
                dEmail,
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        String eFirstName = AES256.encrypt(registrationDto.getFirstName());
        String eLastName = AES256.encrypt(registrationDto.getLastName());
        String eEmail = AES256.encrypt(registrationDto.getEmail());
        User user = new User(
                eFirstName,
                eLastName,
                eEmail,
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
}
