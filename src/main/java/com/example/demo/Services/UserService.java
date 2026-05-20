
package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repositories.JDBCUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JDBCUserRepository userRepository;

    public UserService(JDBCUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public void createUser(String username, String password) {

        User user = new User(0, username, password);

        userRepository.save(user);
    }
}
