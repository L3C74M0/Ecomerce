package com.authservice.service;

import com.authservice.dto.UserRequestDTO;
import com.authservice.entitiy.User;

public interface AuthService {

    User findByEmail(String email);

    String login(String email, String password);

    void register(UserRequestDTO userRequestDTO);

}
