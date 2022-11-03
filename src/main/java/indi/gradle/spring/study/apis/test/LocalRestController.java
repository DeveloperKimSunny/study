package indi.gradle.spring.study.apis.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/local/rest/")
public class LocalRestController {

//      카멜X, 언더바X, 하이픈으로만. 명사의 복수형으로(get, insert 이런거 붙히지마)
//    @GetMapping("repository-tests")

    // RestController는 Controller에 ResponseBody가 이미 붙어있다고 생각하믄됨
    @GetMapping("tests")
    public Map<String, String> tests(){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("test1", "테스트1");
        rtnMap.put("test2", "테스트2");
        return rtnMap;
    }

    @GetMapping("resp-entity-tests")
    public ResponseEntity<Map> responseEntityTests(){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("test1", "테스트1");
        rtnMap.put("test2", "테스트2");

        return ResponseEntity.ok(rtnMap);
    }

}
