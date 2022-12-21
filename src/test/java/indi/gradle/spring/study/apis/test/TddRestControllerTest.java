package indi.gradle.spring.study.apis.test;

import com.google.gson.Gson;
import indi.gradle.spring.study.commons.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// 통합테스트시 사용. 무거워짐
//@SpringBootTest(
//        properties = {
//                "testId=sunny"
//                , "testNames=김선구,ddd"
//        }
//)

// 단위테스트시 사용, @Controller, @RestController 스캔해서 사용 혹은 특정클래스 지정으로인해 @SpringBootTest 보다 가벼움
@WebMvcTest(
        controllers = {TddRestController.class},
        properties = {
                "testId=sunny"
                , "testNames=김선구,ddd"
        }
)
@Slf4j
@AutoConfigureMockMvc
class TddRestControllerTest{

        @Value("${testId}")
        private String testId;
        @Value("${testNames}")
        private String[] testNameArr;

        @Autowired
        MockMvc mockMvc;

        @Test           // 이부분은 기본 로직들 무조건 통과하게끔하는 테스트
        @WithMockUser   // spring security 통과용. org.springframework.security:spring-security-test 필요
        public void getMethodTest() throws Exception {

                log.info("#### tdd Start ####");

                // @SpringBootTest 에 선언된 properties 가져다 쓰기
                log.info("#### prop Id :"+testId);
                Arrays.stream(testNameArr).forEach(streamVal->log.info("#### prop Name :"+streamVal));

                // jsonStr 만들기
                Map<String, String> jsonTest = new HashMap<>();
                jsonTest.put("testId", testId);
                jsonTest.put("testName", testNameArr[0]);
                String jsonContentStr = new Gson().toJson(jsonTest);

                log.info("#### START : MOCK MVC TEST ####");
                log.info("#### /tdd/rest/tdd-get-reqbody-mothod-tests ####");
                mockMvc.perform(
                                get("/tdd/rest/tdd-get-param-mothod-tests")
                                .param("testId", testId)        // 일반적인 request param
                                .header(HttpHeaders.AUTHORIZATION, "Bearer "+"tknStr")  // RequestHeader 에 토큰실을때
                        )
                        .andExpect(status().isOk())
                        .andDo(print());


                log.info("#### /tdd/rest/tdd-get-reqbody-mothod-tests ####");
                mockMvc.perform(
                                get("/tdd/rest/tdd-get-reqbody-mothod-tests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonContentStr)        // RequestBody 에 json문자열 실을때
                                .header(HttpHeaders.AUTHORIZATION, "Bearer "+"tknStr")  // RequestHeader 에 토큰실을때
                        )
                        .andExpect(status().isOk())
                        .andDo(print());

                log.info("#### END : MOCK MVC TEST ####");

        }

        @Test           // 예상한 익셉션 발생하는지 테스트
        public void exceptioonTest() throws Exception {
                log.info("#### START : ASSERTIONS ####");

                Exception exception = Assertions.assertThrows(Exception.class, ()->{
                        TddRestController controller = new TddRestController();
                        controller.exceptionTest(-1);
                        log.info("###### assertThrows");
                });
                // 예상한 오류 문자열 아니면 찍어냄
//                assertEquals(CustomException.class, exception.getClass());
                assertThat(CustomException.class).isEqualTo(exception.getClass());

                log.info("#### END : ASSERTIONS ####");
        }
}