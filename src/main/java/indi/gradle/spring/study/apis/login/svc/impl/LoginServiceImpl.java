package indi.gradle.spring.study.apis.login.svc.impl;

import indi.gradle.spring.study.apis.login.dto.LoginDto;
import indi.gradle.spring.study.apis.login.svc.LoginService;
import indi.gradle.spring.study.commons.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String createTestJwtTkn(String subjectStr) {
        return jwtTokenProvider.createToken(subjectStr);
    }

    @Override
    public String createTestJwtTkn() {
        return jwtTokenProvider.createToken(
                LoginDto.builder()
                        .userId("devtest123")
                        .userName("개발테스트")
                        .build()
        );
    }

    @Override
    public String getJwtTknSubject(String tknStr) {
        return jwtTokenProvider.getSubject(tknStr);
    }

    @Override
    public Object getTknValue(String tknStr, String claimsKey) {
        return jwtTokenProvider.getTknValue(tknStr, claimsKey);
    }
}
