package fitness.securityserver.service;

import fitness.securityserver.config.EncryptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginRequestService {

    private final RestTemplate restTemplate;

    // 로그인 서버 URL (실제 서버 주소로 변경)
    private final String LOGIN_SERVER_URL = "http://localhost:8081/api/auth/login";

    public LoginRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendLoginRequest(String id, String password) {
        try {
            // ID와 비밀번호 암호화
            String encryptedId = EncryptionUtil.encrypt(id);
            String encryptedPassword = EncryptionUtil.encrypt(password);

            // 로그인 서버로 보낼 요청 데이터 구성
            Map<String, String> loginData = new HashMap<>();
            loginData.put("id", encryptedId);
            loginData.put("password", encryptedPassword);

            // 로그인 서버로 POST 요청 전송
            String response = restTemplate.postForObject(LOGIN_SERVER_URL, loginData, String.class);

            return response;  // 로그인 서버의 응답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "암호화 오류";
        }
    }
}
