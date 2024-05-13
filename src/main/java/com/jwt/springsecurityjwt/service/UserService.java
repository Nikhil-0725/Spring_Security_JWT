package com.jwt.springsecurityjwt.service;

import com.jwt.springsecurityjwt.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

}
