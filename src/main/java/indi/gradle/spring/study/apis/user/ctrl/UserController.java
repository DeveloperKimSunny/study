package indi.gradle.spring.study.apis.user.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users/")
public class UserController {

    @GetMapping("test")
    public ResponseEntity<Map> test(){

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("test", "성공");

        return ResponseEntity.ok(rtnMap);
    }
}
