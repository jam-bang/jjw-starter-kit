package com.jjw.starter.user;

import com.jjw.starter.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserDto.Response>> list() {
        return ApiResponse.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDto.Response> get(@PathVariable Long userId) {
        return ApiResponse.ok(userService.findById(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDto.Response> create(@Valid @RequestBody UserDto.CreateRequest request) {
        return ApiResponse.ok(userService.create(request));
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserDto.Response> update(@PathVariable Long userId,
                                                @Valid @RequestBody UserDto.UpdateRequest request) {
        return ApiResponse.ok(userService.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
