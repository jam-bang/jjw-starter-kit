package com.jjw.starter.user;

import com.jjw.starter.common.BusinessException;
import com.jjw.starter.common.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;

    public List<UserDto.Response> findAll() {
        return userMapper.findAll().stream().map(UserDto.Response::from).toList();
    }

    public UserDto.Response findById(Long userId) {
        return UserDto.Response.from(getUser(userId));
    }

    @Transactional
    public UserDto.Response create(UserDto.CreateRequest request) {
        // 로그인 ID 중복은 업무 오류로 처리
        if (userMapper.existsByLoginId(request.loginId())) {
            throw new BusinessException(ErrorCode.DUPLICATED, "이미 사용 중인 로그인 ID입니다.");
        }
        User user = new User();
        user.setLoginId(request.loginId());
        user.setUserName(request.userName());
        user.setEmail(request.email());
        userMapper.insert(user);
        return findById(user.getUserId());
    }

    @Transactional
    public UserDto.Response update(Long userId, UserDto.UpdateRequest request) {
        User user = getUser(userId);
        user.setUserName(request.userName());
        user.setEmail(request.email());
        userMapper.update(user);
        return UserDto.Response.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        if (userMapper.delete(userId) == 0) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "사용자를 찾을 수 없습니다. id=" + userId);
        }
    }

    private User getUser(Long userId) {
        return userMapper.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "사용자를 찾을 수 없습니다. id=" + userId));
    }
}
