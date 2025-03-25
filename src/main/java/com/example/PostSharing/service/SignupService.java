package com.example.PostSharing.service;

import com.example.PostSharing.data.Role;
import com.example.PostSharing.dto.SignupDTO;
import com.example.PostSharing.entity.UserEntity;
import com.example.PostSharing.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String signUp(SignupDTO signupDTO) {

        if (userRepository.findByUsername(signupDTO.getUsername()).isPresent()){
            return "Username already taken";
        }
        UserEntity admin = new UserEntity();
        admin.setUsername(signupDTO.getUsername());
        admin.setEmail(signupDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        return "User created";
    }
}
