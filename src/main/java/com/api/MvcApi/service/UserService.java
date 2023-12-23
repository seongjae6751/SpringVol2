package com.api.MvcApi.service;

import com.api.MvcApi.domain.User;

import java.util.ArrayList;

public interface UserService {
    // 모든 유저 조회
    ArrayList<User> findAll();
    // 특정 유저 조회
    User findById(String id);
    // 유저 생성
    User createUser(User newUser);
    // 유저 수정
    void updateUsername(String id, String newUsername);
    // 유저 삭제
    void deleteUser(String id);
}
