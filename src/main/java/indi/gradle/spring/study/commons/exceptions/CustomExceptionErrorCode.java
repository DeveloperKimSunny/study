package indi.gradle.spring.study.commons.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionErrorCode {

    // 사용자가 예측할 수 있는 Exception 모음. status, code, message 커스텀
    //== 401 (인증관련)  ==//
    EMPTY_JWT_TOKEN(HttpStatus.UNAUTHORIZED, 40100,"토큰값이 필요합니다."),
    UNAUTHORIZED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, 40101,"유효하지 않은 토큰입니다."),

    //== 400 ==//
    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST, 40300,"지원하지 않는 Http Method 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, 40301,"유효하지 않은 Request Body 혹은 Argument입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, 40302, "해당 사용자를 찾을 수 없습니다."),
    ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, 40303, "해당 상품을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

}
