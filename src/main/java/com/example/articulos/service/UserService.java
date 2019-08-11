package com.example.articulos.service;

import com.example.articulos.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}