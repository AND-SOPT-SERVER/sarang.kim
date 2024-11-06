package org.sopt.diary.api;

import org.sopt.diary.dto.user.*;
import org.sopt.diary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody SignupRequest request) {
        Long userId = userService.signup(
                request.getUsername(),
                request.getPassword(),
                request.getNickname()
        );
        return ResponseEntity.ok(new UserResponse(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}