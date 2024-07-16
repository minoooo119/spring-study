package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//컨트롤러는 애초에 View를 반환하기 위해서 사용된다.
//client 요청에 따라 View를 반환하게 된다.
//Response Body 어노테이션을 활용해서 Json 형태로 데이터를 반환할 수 도 있다.

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){//이 url에 매챙이 된다면 컨트롤러가 실행되고
        //만약 해당 url에 대한 컨트롤러가 없다면 view resolver가 static한 html을 찾아서 렌더링한다.

        //mapping 해준다 data, hello!!
        model.addAttribute("data","hello!!");
        //view resolver에 return hello를 해주게 된다.
        //그러면 hello.html을 찾아서 렌더링하게 된다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        //url로 인자를 넘겨서 name에 값을 넣어주게 된다.
        model.addAttribute("name",name);
        return "hello-template";
    }

    //html로 내리느냐, api로 데이터를 내리느냐가 있다.
    @GetMapping("hello-string")
    @ResponseBody //이런 방식은 큰 의미는 없긴함.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api") //객체를 받고 ResponseBody로 반환하면 Json 방식으로 반환한다.
    @ResponseBody // 이 어노테이션을 활용하면 http의 body에 문자 내용을 직접 반환한다. view resolver 대신에 http message converter가 동작한다.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //json 방식으로 리턴을 해주게 된다.
    }
    static class Hello{
        private String name;
        // 자바 빈 표준 방식 등 언어가 있음
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
