package org.sopt.diary.service;

import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long signup(String username, String password, String nickname) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity user = new UserEntity(username, password, nickname);
        UserEntity savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        // 간단한 식별자 반환
        return user.getId().toString();
    }
}