package indi.gradle.spring.study.commons.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    // 예측 불가능한 익셉션 발생시 리턴
    @ExceptionHandler(Throwable.class)
    public ApiExceptionResponse defaultHandler(HttpServletRequest req, Throwable t){
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        httpHeaders.setContentType(mediaType);

        log.error("[defaultException] : ", t);

        return ApiExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(t.getMessage())
                .url(req.getRequestURI())
                .build();
    }

    // 예측 가능한 익셉션 발생시 리턴
    // 오류로그는 따로 안뽑아줌.
    @ExceptionHandler(CustomException.class)
    public ApiExceptionResponse customExceptionHandler(HttpServletRequest req, CustomException ex){
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        httpHeaders.setContentType(mediaType);

        log.error("[handleCustomException] {} : {}", ex.getCustomExceptionErrorCode().name(), ex.getCustomExceptionErrorCode().getMessage());

        return ApiExceptionResponse.builder()
                .status(ex.getCustomExceptionErrorCode().getStatus())
                .statusCode(ex.getCustomExceptionErrorCode().getCode())
                .message(ex.getCustomExceptionErrorCode().getMessage())
                .url(req.getRequestURI())
                .build();
    }

}
