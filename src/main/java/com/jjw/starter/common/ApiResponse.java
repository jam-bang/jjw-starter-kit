package com.jjw.starter.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 모든 API 응답을 같은 형식으로 감싸는 공통 응답 객체.
 * 성공: { "success": true, "data": ... }
 * 실패: { "success": false, "code": "...", "message": "..." }
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, T data, String code, String message) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode, String message) {
        return new ApiResponse<>(false, null, errorCode.name(), message);
    }
}
