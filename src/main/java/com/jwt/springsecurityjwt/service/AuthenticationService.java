package com.jwt.springsecurityjwt.service;

import com.jwt.springsecurityjwt.entity.User;
import com.jwt.springsecurityjwt.utils.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(User request);
    AuthenticationResponse authenticate(User request);
}
