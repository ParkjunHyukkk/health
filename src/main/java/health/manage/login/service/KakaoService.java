package health.manage.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;



@Service
public class KakaoService {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Value("${kakao.login.url}")
    private String url;

    public HashMap<String,String> getKakaoToken(String code){
        HashMap<String,String> result = new HashMap<>();
        result.put("token",code);
        // 여기에 코드 작성
        System.out.println(sendUrl(code));
        return result;
    }

    public String sendUrl(String code){
        try {

            String sb =
                    "grant_type=authorization_code" +
                    "&client_id=" + // REST_API_KEY
                    "&redirect_uri=" + // REDIRECT_URI
                    "&code=" + code;
            // Create an HttpClient object
            HttpClient httpClient = HttpClient.newHttpClient();
            // Create an HttpRequest object with the desired request method and URL, and set the request body using the ofString method of the BodyPublishers class:
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(sb))
                    .uri(URI.create(url))
                    .build();
            // Send the request using the send method of the HttpClient object and get the response using the body method of the HttpResponse object:
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body().toString();

            // 여기서 responseBody를 처리할 수 있음
            return responseBody;
        } catch (Exception e) {
            // 예외 처리
            log.info("error",e);
            return null; // 예외 발생 시 null을 반환하거나 적절한 값을 반환할 수 있음
        }
    }

}