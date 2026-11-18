package com.jjw.starter.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/** 사용자 API 입출력 DTO 모음 (도메인 객체를 그대로 노출하지 않는다) */
public final class UserDto {

    private UserDto() {
    }

    public record CreateRequest(
            @NotBlank @Size(max = 30) String loginId,
            @NotBlank @Size(max = 50) String userName,
            @NotBlank @Email String email) {
    }

    public record UpdateRequest(
            @NotBlank @Size(max = 50) String userName,
            @NotBlank @Email String email) {
    }

    public record Response(Long userId, String loginId, String userName, String email, LocalDateTime createdAt) {

        public static Response from(User user) {
            return new Response(user.getUserId(), user.getLoginId(), user.getUserName(),
                    user.getEmail(), user.getCreatedAt());
        }
    }
}
