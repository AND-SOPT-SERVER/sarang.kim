package org.sopt.diary.dto.user;

public class UserResponse {
    private Long userId;

    public UserResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() { return userId; }
}