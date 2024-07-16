package hello.hello_spring.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
    @RequestMapping(value = "/domain",method = RequestMethod.POST)
    public String postExample(){
        return "Hello POST API";
    }

    // RequestBody와 Map을 활용한 Post Api 구현

    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String,Object> postData){
        StringBuilder sb=new StringBuilder();

        postData.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
        return sb.toString();
    }
}

