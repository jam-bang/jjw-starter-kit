package com.jjw.starter.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jjw.starter.common.BusinessException;
import com.jjw.starter.common.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void 사용자를_등록하고_조회한다() {
        UserDto.Response created = userService.create(
                new UserDto.CreateRequest("hong", "홍길동", "hong@example.com"));

        assertThat(created.userId()).isNotNull();
        assertThat(userService.findById(created.userId()).userName()).isEqualTo("홍길동");
    }

    @Test
    void 로그인ID가_중복되면_DUPLICATED_예외() {
        userService.create(new UserDto.CreateRequest("dup", "중복", "dup@example.com"));

        assertThatThrownBy(() -> userService.create(new UserDto.CreateRequest("dup", "중복2", "dup2@example.com")))
                .isInstanceOf(BusinessException.class)
                .extracting(e -> ((BusinessException) e).getErrorCode())
                .isEqualTo(ErrorCode.DUPLICATED);
    }

    @Test
    void 없는_사용자를_삭제하면_NOT_FOUND_예외() {
        assertThatThrownBy(() -> userService.delete(9999L))
                .isInstanceOf(BusinessException.class)
                .extracting(e -> ((BusinessException) e).getErrorCode())
                .isEqualTo(ErrorCode.NOT_FOUND);
    }
}
