package com.nari.techshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public User register(User user) {

        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                                  .orElse(null);

        if (user != null &&
            user.getPassword().equals(password)) {

            return jwtUtil.generateToken(email);
        }

        return "Invalid Email or Password";
    }

}