package com.api.MvcApi.service;

import com.api.MvcApi.domain.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    // 모든 유저 조회
    List<User> findAll();
    // 특정 유저 조회
    User findById(String id);
    // 유저 생성
    User createUser(User newUser);
    // 유저 수정
    void updateUsername(@RequestBody String id, @RequestBody User user);
    // 유저 삭제
    void deleteUser(String id);

}
