package com.example.demo.Repositories;

import com.example.demo.Models.User;

public interface UserRepository {

    User findByUsername(String username);

    void save(User user);
}
