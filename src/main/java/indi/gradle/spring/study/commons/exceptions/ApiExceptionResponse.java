package indi.gradle.spring.study.commons.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
public class ApiExceptionResponse implements Serializable {

    private String message;
    private HttpStatus status;
    private int statusCode;
    private String url;

}
