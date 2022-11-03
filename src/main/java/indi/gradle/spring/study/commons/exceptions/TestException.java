package indi.gradle.spring.study.commons.exceptions;

import lombok.Getter;

@Getter
public class TestException extends RuntimeException{
    public TestException(){
        super("#### TEST EXCEPTION #####");
    }

}
