package com.jjw.starter.user;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** TB_USER 테이블과 매핑되는 도메인 객체 */
@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long userId;
    private String loginId;
    private String userName;
    private String email;
    private LocalDateTime createdAt;
}
