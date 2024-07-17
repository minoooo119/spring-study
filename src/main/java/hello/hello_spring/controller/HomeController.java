package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 먼저 컨트롤러에 url이 존재하는지 찾고 -> 없으면 static 한 html을 띄어준다.
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
