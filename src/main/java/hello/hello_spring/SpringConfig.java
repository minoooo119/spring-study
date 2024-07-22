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
    //spring data jpa 가 알아서 구현체를 만들어 주기에 위에 처럼 그냥 가져다가 쓰면 된다!
    //아래처럼 인젝션 받으면 된다.

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

//아래 경우는 JPA 만 사용하는 경우의 예시이다.

//package hello.hellospring;
//import hello.hellospring.repository.*;
//import hello.hellospring.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;

//@Configuration
//public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
//}