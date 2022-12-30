package indi.gradle.spring.study.commons.conf.interceptor;

import indi.gradle.spring.study.apis.login.dto.LoginDto;
import indi.gradle.spring.study.commons.exceptions.CustomException;
import indi.gradle.spring.study.commons.exceptions.CustomExceptionErrorCode;
import indi.gradle.spring.study.commons.utils.jwt.AuthorizationExtractor;
import indi.gradle.spring.study.commons.utils.jwt.JwtTokenProvider;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(">>> BearerAuthInterceptor.prehandle");
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
