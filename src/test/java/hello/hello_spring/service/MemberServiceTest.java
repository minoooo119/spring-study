package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService service = new MemberService();
//
//    //얘가 서비스에서 관리하는 객체랑 같은게 맞는지.. 의문... static 이어서 그렇다고는 한다.. 하지만 instance 는 다르니까 바꾸자.
//    // service 의 일부를 수정함.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService service;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // 이런 것을 DI -> dependency injection
        memberRepository = new MemoryMemberRepository();
        service = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //아래 과정으로 테스트를 진행하는 것이 좋다.

        //given
        Member member1 = new Member();
        member1.setName("1");
        Member member2 = new Member();
        member2.setName("2");
        Member member3 = new Member();
        member3.setName("3");
        //when
        Long saveId1 = service.join(member1);
        Long saveId2 = service.join(member2);
        Long saveId3 = service.join(member3);

        //then
        Member findMember1 = service.findOne(saveId1).get();
        assertThat(member1.getName()).isEqualTo(findMember1.getName());
        Member findMember2 = service.findOne(saveId2).get();
        assertThat(member2.getName()).isEqualTo(findMember2.getName());
        Member findMember3 = service.findOne(saveId3).get();
        assertThat(member3.getName()).isEqualTo(findMember3.getName());
    }
    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        service.join(member1);

        //then
//        try {
//            service.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // 위처럼 할바에 다른 문법을 활용한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        // 이건 오른쪽 실행시 IllegalStateException 발생해야 테스트 성공
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 이렇게 메시지, 예외 둘 다 검증할 수 있다.
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}