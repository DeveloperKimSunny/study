package indi.gradle.spring.study.commons.conf.interceptor;

import indi.gradle.spring.study.commons.exceptions.CustomException;
import indi.gradle.spring.study.commons.exceptions.CustomExceptionErrorCode;
import indi.gradle.spring.study.commons.utils.jwt.AuthorizationExtractor;
import indi.gradle.spring.study.commons.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {

    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>> BearerAuthInterceptor.prehandle");
        String token = authorizationExtractor.extract(request, "Bearer");

        if(token.isEmpty()){
            throw new CustomException(CustomExceptionErrorCode.EMPTY_JWT_TOKEN);
        }

        if(!jwtTokenProvider.validateToken(token)){
            throw new CustomException(CustomExceptionErrorCode.UNAUTHORIZED_JWT_TOKEN);
        }

//        LoginDto loginDto = (LoginDto) jwtTokenProvider.getTknValue(token, "userInfo");
//        request.setAttribute("userInfo", loginDto);

        return true;
    }
}
