package hello.hello_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// rest controller를 사용해서
// 간단하게 외부의 요청을 받아 응답하는 기능을 구현

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // http://localhost:8080/api/v1/get-api/hello
    // get 형식의 요청만 받기 위해 아래와 같이 설정 추가 -> 번거로워서 나중엔 새로나온 어노테이션 활용
    // ex) @GetMapping, @PostMapping, @PutMapping, @DeleteMapping

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
        return "Hello World";
    }

    // 1. 매개변수 없는 간단한 GET 메서드 구현
    @GetMapping(value = "/name")
    public String getName(){
        return "mino";
    }

}
