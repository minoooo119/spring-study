package hello.hello_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController는 @Controller 에 @ResponseBody 가 추가된 것
//주용도는 Json 형태로 객체 데이터를 반환하는 것임.
//주로 REST API 개발을 할 때 사용하며, 객체를 ResponseEntity 로 감싸서 반환한다.

//@RestController = @Controller + @ResponseBody 완벽하게 동일하다.

@RestController
public class HelloRestController {
    @RequestMapping("/hello-req")
    public String hello(){
        return "hello world";
    }
}
