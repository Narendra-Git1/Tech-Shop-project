package com.nari.techshop.service;

import com.nari.techshop.entity.User;

public interface AuthService {

    User register(User user);

    String login(String email, String password);

}