package indi.gradle.spring.study.apis.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private String userId;
    private String userName;
}
