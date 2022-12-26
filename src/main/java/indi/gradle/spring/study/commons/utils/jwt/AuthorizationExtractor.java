package indi.gradle.spring.study.commons.utils.jwt;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class AuthorizationExtractor {
    public static final String AHTHORIZATION = "Authorization";
    public static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName();

    public String extract(HttpServletRequest request, String type){
        Enumeration<String> headers = request.getHeaders(AHTHORIZATION);
        while(headers.hasMoreElements()){
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type.toLowerCase())){
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }
}
