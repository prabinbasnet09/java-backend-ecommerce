package com.basnetpr.user.service;

import com.basnetpr.user.entity.User;
import com.basnetpr.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(User user) {
        log.info("Inside createNewUser of UserService");
        return userRepository.save(user);
    }

    public User loginUser(User user) {
        log.info("Inside loginUser of UserService");
        User dbUser = userRepository.findByUsername(user.getUsername());
        return dbUser;
    }

    public List<User> getAllUsers() {
        log.info("Inside getAllUsers of UserService");
        List<User> usersList = userRepository.findAll();
        return usersList;
    }
}
