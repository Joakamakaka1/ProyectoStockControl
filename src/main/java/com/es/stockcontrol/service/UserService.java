package com.es.stockcontrol.service;

import com.es.stockcontrol.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
