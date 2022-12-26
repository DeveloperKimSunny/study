package indi.gradle.spring.study.apis.login.svc;

public interface LoginService {
    String createTestJwtTkn();
    String createTestJwtTkn(String subjectStr);
    String getJwtTknSubject(String tknStr);
    Object getTknValue(String tknStr, String claimsKey);
}
