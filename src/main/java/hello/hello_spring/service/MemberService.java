package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 커멘드 + 시프트 + T 를 통해 알아서 테스트 인터페이스 만들어줌.

public class MemberService {

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * 1. 같은 이름의 회원 중복 방지
    * */
    public Long join(Member member){
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        //optional 을 활용해서 아래와 같이 할 수 있다.
//        result.ifPresent(m ->{
//            //예외 처리 해줄 수 있다.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // 깔끔하게 아래처럼 가능하다.
//        memberRepository.findByName(member.getName())
//                        .ifPresent(m->{
//                            throw new IllegalStateException("이미 존재하는 회원입니다.")
//                        });
        // 그리고 그냥 함수로 뽑아서 사용해라
        // 컨트롤 + T 눌러서 가능하다.
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 이거에 대해 테스트 만들어서 확인해보자구!
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
