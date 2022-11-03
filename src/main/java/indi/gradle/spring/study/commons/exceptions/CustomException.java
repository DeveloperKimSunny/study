package indi.gradle.spring.study.commons.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    public CustomException(){
        super("#### CUSTOM EXCEPTION #####");
    }

}
