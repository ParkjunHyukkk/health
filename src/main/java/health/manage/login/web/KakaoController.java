package health.manage.login.web;

import org.springframework.web.bind.annotation.*;

@RestController
public class KakaoController {

    /* 카카오 로그인 API
     * - 인가 코드 받기
     */
    @ResponseBody
    @GetMapping("/getAuthorization")
    public String getAuthorization(@RequestParam("code") String code){
        System.out.println(code);
        return "통과";
    }
}
