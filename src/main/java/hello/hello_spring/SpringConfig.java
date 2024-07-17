package hello.hello_spring;

import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private EntityManager em;
    private final MemberRepository memberRepository;

    //생성자가 하나인 경우에느 Autowired 생략가능
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {// 두 개 이상이 autowired 되지 않게 memberRepository 하나만 등록할 것!
//        알아서 스프링이 springDataJPAMemberRepository에 대해서 구현체를 등록해 놓고 그거를 가져다가 쓰는 것
        this.memberRepository = memberRepository;
    }

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        //EntityManager 는 알아서 애초에 컨테이너에 존재함
//        //Config 에 의존성을 주입해서 수동 생성한 것들 다 고려한다.
//        this.em = em;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JpaMemberRepository(em);
//
//    }

}
