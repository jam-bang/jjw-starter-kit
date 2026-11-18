package com.jjw.starter.user;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/** SQL은 resources/mapper/UserMapper.xml 에 작성 (바인드 변수 #{} 필수) */
@Mapper
public interface UserMapper {

    List<User> findAll();

    Optional<User> findById(Long userId);

    boolean existsByLoginId(String loginId);

    void insert(User user);

    int update(User user);

    int delete(Long userId);
}
