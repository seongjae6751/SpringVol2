package com.api.MvcApi.service;

import com.api.MvcApi.domain.User;
import com.api.MvcApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        try{
            userList = userRepo.findAll();
        } catch (SQLException e) {
            log.error("findAll service error = {}", e);
        }
        return userList;
    }

    @Override
    public User findById(String id) {
        User user = new User();
        try {
            user = userRepo.findById(id);
        } catch (SQLException e) {
            log.error("findById service error = {}", e);
        }
        return user;
    }

    @Override
    public User createUser(User newUser) {
        User user = new User();
        try {
            user = userRepo.createUser(newUser);
        } catch (SQLException e) {
            log.error("createUser service error = {}", e);
        }
        return user;
    }

    @Override
    public void updateUsername(String id, String newUsername) {
        try {
            userRepo.updateUsername(id, newUsername);
        } catch (SQLException e) {
            log.error("updateUserName service error = {}", e);
        }
    }

    @Override
    public void deleteUser(String id) {
        try {
            userRepo.deleteUser(id);
        } catch (SQLException e) {
            log.error("deleteUser service error = {}", e);
        }
    }
}
