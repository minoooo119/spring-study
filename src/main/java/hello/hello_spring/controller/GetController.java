package hello.hello_spring.controller;

import org.springframework.web.bind.annotation.*;

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

    // 2. @PathVariable 을 활용한 GET 메서드 구현
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    // 3. @PathVariable 에 변수명을 매핑하는 방법
    // 2번 처럼 variable에 다른 변수 이름을 주고 싶을 때
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

}
