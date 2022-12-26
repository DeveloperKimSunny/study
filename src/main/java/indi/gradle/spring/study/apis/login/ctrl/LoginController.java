package indi.gradle.spring.study.apis.login.ctrl;

import indi.gradle.spring.study.apis.login.svc.LoginService;
import indi.gradle.spring.study.apis.login.svc.impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("test-jwt-tkn")
    public ResponseEntity<Map> testJwtTkn(){
        String tknStr = loginService.createTestJwtTkn();
        Object tknValue = loginService.getTknValue(tknStr, "userInfo");

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("accessTkn", tknStr);
        rtnMap.put("tknSubject", tknValue);

        return ResponseEntity.ok(rtnMap);
    }

}
