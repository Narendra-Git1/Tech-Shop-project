package com.nari.techshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nari.techshop.entity.User;
import com.nari.techshop.repository.UserRepository;
import com.nari.techshop.service.AuthService;
import com.nari.techshop.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                                  .orElse(null);

        if (user != null &&
            passwordEncoder.matches(
                    password,
                    user.getPassword()
            )) {

            return jwtUtil.generateToken(email);
        }

        return "Invalid Email or Password";
    }

}