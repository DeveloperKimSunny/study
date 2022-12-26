package indi.gradle.spring.study.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/local/")
public class LocalController {

//      카멜X, 언더바X, 하이픈으로만. 명사의 복수형으로(get, insert 이런거 붙히지마)
//    @GetMapping("repository-tests")

    // 일반적인 Controller에서는 viewresolver 동작으로 인한 페이지를 찾음.
    // 데이터만 반환하려면 responseBody만 던져주어야함(ajax 생각하믄됨)
    @GetMapping("tests")
    @ResponseBody
    public Map<String, String> tests(){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("test1", "테스트1");
        rtnMap.put("test2", "테스트2");

        return rtnMap;
    }

    @GetMapping("resp-entity-tests")
    @ResponseBody
    public ResponseEntity<Map> responseEntityTests(){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("test1", "테스트1");
        rtnMap.put("test2", "테스트2");

        return ResponseEntity.ok(rtnMap);
    }

}
