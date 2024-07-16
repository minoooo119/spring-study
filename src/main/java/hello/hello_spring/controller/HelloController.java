package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
