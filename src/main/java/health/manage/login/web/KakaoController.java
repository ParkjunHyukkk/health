package health.manage.login.web;

import health.manage.login.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class KakaoController {

    @Autowired
    private KakaoService service;

    /* 카카오 로그인 API
     * 인가 코드를 이용하여 token 발급 받기
     */
    @ResponseBody
    @RequestMapping("/kakao/login")
    public HashMap<String,String> getAuthorization(@RequestParam("code") String code){
        HashMap<String,String> token = new HashMap<>();
        token = service.getKakaoToken(code);
        return token;
    }


}
