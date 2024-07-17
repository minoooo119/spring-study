package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface 가 interface 를 받을 때는 implements 가 아니라 extends 임
public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 이 것만 하면 끝이라는데..? 왜 지
    // 자동으로 구현체를 만들어서 등록을 해준다..!
    // 어떻게 쓰는가
//    private final MemberRepository memberRepository;
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    // 이렇게 사용할 때 인젝션하면 끝!


    @Override
    // JPQL -> select m from Member m where m.name = ? 이런식으로 작성해준다.
    Optional<Member> findByName(String name);

    //왠만하면 JpaRepository 에 기본적인 query 들은 저장이 되어있다.
    //그래서 일반적이지 않은 새로운 것들만 추가해주면 된다.
//    인터페이스를 통한 기본적인 CRUD 제공 된다. create, read, update, delete 관련
}
