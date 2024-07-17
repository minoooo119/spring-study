package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 서비스를 통해 멤버를 가입하고 저장하게 된다.
// 화면을 추가하고 싶다
// 그러려면 멤버 컨트롤러 필요
// 멤버 컨트롤러가 멤버 서비스를 통해 가입하고 조회하게 된다.

// 이렇게 되면 컨테이너에 멤버 컨트롤러 객체가 생성되고 스프링이 관리를 하게 된다.
// => 스프링 bean 이 관리된다고 한다.


@Controller
public class MemberController {
    private final MemberService memberService;

    // DI 관련 방법 3가지
    // 1. 필드 주입 방법
//     @Autowired private MemberService memberService;
    // 별로 좋지 않은 방법이대.

    // 2. Setter 주입 방법
//    private MemberService memberService;
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    // 멤버 서비스를 누가 바꿔치지 할 수도.. public 하게 노출이 되므로 좋지않다.

    // 3. Constructor 주입 방법
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
    // 이렇게 의존성을 주입하는 것이 가장 일반적이다.
    // 생성 시점에만 주입하고 더 이상 수정 없도록 하는 것
    // 동적으로 변하는 경우가 없기때문에 이렇게 하는 것이 좋다.

    // 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
    // 정형화되지 않거나 구현 클래스를 변경해야하면 설정을 통해 스프링 빈으로 등록한다.

    // ex) 리포지토리 관련해서 -> 인터페이스를 통해 다른 리포지토리로 변경할 때


//    private final MemberService memberService = new MemberService();
    //이렇게 new 로 새로운 객체를 생성하지 않고 공용으로 같이 쓰는게 좋음
    //스프링 컨테이너에 등록하고 가져와서 쓰는게 좋다.

    // 'hello.hello_spring.service.MemberService' that could not be found.
    // 스프링이 찾을 수 있게 해야됨.
    // 별 다른 것을 해주지 않으면 그냥 자바 클래스 이므로 못 찾는다. @Service 추가 해줘야한다.

    // 컴포넌트 스캔과 자동 의존관계 설정 -> Controller, Service, Repository 등
    // 컴포턴트 어노테이션이 있으면 스프링이 객체 생성해서 컨테이너에 자동 등록해준다.
    // 스프링 빈으로 자동 등록이 된다. -> 싱글톤으로 등록한다. (싱글톤 = 유일하게 하나 둬서 공유, 동시에 접근은 안됌)

    // 자동 의존 관게 설정은 Autowired 를 통해서 연결이 된다.

    //직접 등록해서 의존 관계를 주입시켜줄 수도 있다.

    @Autowired
    public MemberController(MemberService memberService) {
        //memberController 가 호출되면 의존관계가 있는 memberService 를 컨테이너에서 찾아서 넣어준다.
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){//해당 객체에 맞게 저장이 된다.
        // form 태그에 맞는 key 에 맞게 위 객체에 알맞게 값이 들어가게 된다.
        // 그래서 변수도 잘 맞춰줘야한다.
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        // redirect 해준다.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
