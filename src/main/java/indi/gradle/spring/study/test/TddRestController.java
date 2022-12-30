package indi.gradle.spring.study.test;

import indi.gradle.spring.study.commons.exceptions.CustomException;
import indi.gradle.spring.study.commons.exceptions.CustomExceptionErrorCode;
import indi.gradle.spring.study.commons.exceptions.TestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j // spring-boot-starter-web 이 impl되어있으면 사용가능. 로그백
@RequestMapping("/tdd/rest/")
public class TddRestController {

    @GetMapping("mothod-tests")
    public ResponseEntity<Map> getMethodTest(){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("test1", "테스트1");
        rtnMap.put("test2", "테스트2");

        log.info("#### slf4j");
        return ResponseEntity.ok(rtnMap);
    }

    @GetMapping("tdd-get-param-mothod-tests")
    public ResponseEntity<Map> tddGetMethodTest(@RequestParam(name = "testId") String testId){
        Map<String, String> rtnMap = new HashMap<>();
        rtnMap.put("result", testId);

        log.info("#### slf4j");
        return ResponseEntity.ok(rtnMap);
    }

    @GetMapping("tdd-get-reqbody-mothod-tests")
    public ResponseEntity<Map> tddGetMethodTest(@RequestBody  Map params){
        return ResponseEntity.ok(params);
    }

    @GetMapping("exception-tests")
    public Map exceptionTest(@RequestParam(name = "intVal") int intVal) throws Exception {
        if(intVal < 0){
            throw  new CustomException(CustomExceptionErrorCode.NOT_SUPPORTED_HTTP_METHOD);
        }else if(intVal > 0){
            throw new TestException();
        }else{
            return new HashMap();
        }
    }

}
