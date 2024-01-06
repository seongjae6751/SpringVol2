package com.api.MvcApi.controller;

import com.api.MvcApi.domain.User;
import com.api.MvcApi.domain.UserRepository;
import com.api.MvcApi.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.api.MvcApi.security.JwtTokenProvider;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .username(user.get("username"))
                .password(passwordEncoder.encode(user.get("password")))
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByName(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername());
    }

    @GetMapping("")
    public List<User> findAll(){
        return userServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public User findUsers(@PathVariable(name = "id") String id){
        return userServiceImpl.findById(id);
    }

    @PostMapping("")
    public User createUser(User newUser) {
        return userServiceImpl.createUser(newUser);
    }

    @PutMapping ("/{id}")
    public void updateUserName(@RequestParam @PathVariable(name = "id") String id,  @RequestBody User user) {
        userServiceImpl.updateUsername(id, user);
    }


    @DeleteMapping("/{id}")
    public void deleteUsers(@PathVariable(name = "id") String id){
        userServiceImpl.deleteUser(id);
    }

}
