package com.jwt.springsecurityjwt.service.impl;

import com.jwt.springsecurityjwt.entity.User;
import com.jwt.springsecurityjwt.repository.UserRepository;
import com.jwt.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
