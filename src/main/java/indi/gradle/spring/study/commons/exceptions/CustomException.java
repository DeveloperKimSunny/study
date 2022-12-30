package indi.gradle.spring.study.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private CustomExceptionErrorCode customExceptionErrorCode;

    public CustomException(){
//        super("#### CUSTOM EXCEPTION #####");
        new CustomException(CustomExceptionErrorCode.SUCCESS);
    }

    public CustomException(CustomExceptionErrorCode errorCode){
        this.customExceptionErrorCode = errorCode;
    }

}
