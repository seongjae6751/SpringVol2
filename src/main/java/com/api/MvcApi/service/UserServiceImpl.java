package com.api.MvcApi.service;

import com.api.MvcApi.domain.User;
import com.api.MvcApi.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User newUser) {
        return userRepo.save(newUser);
    }

    @Override
    public void updateUsername(String id, User user) {
        Optional<User> updateUser = userRepo.findById(id);

        updateUser.ifPresent(selectUser -> {
            selectUser.setUsername(user.getUsername());
            selectUser.setPassword(user.getPassword());

            userRepo.save(selectUser);
        });
    }

    @Override
    public void deleteUser(String id) {
        Optional<User> updateUser = userRepo.findById(id);

        updateUser.ifPresent(selectUser -> {
            userRepo.delete(selectUser);
        });
    }
}
