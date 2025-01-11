package me.hello.backend.service;

import me.hello.backend.model.User;
import me.hello.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.insertUser(user);
    }

    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public void updateUser(String id, User user) {
        userRepository.updateUser(id, user);
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }
}