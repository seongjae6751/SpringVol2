package com.api.MvcApi.controller;

import com.api.MvcApi.domain.User;
import com.api.MvcApi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public ArrayList<User> FindAll(){
        return userServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public User FindUsers(@PathVariable(name = "id") String id){
        return userServiceImpl.findById(id);
    }

    @PostMapping("")
    public User CreateUser(User newUser) {
        return userServiceImpl.createUser(newUser);
    }

    @PutMapping ("/{id}")
    public void UpdateUserName(@PathVariable(name = "id") String id,  @RequestBody String newUsername) {
        userServiceImpl.updateUsername(id, newUsername);
    }


    @DeleteMapping("/{id}")
    public void DeleteUsers(@PathVariable(name = "id") String id){
        userServiceImpl.deleteUser(id);
    }

}
