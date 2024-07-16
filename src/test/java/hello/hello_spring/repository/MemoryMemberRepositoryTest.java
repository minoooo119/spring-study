package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// TTD test 주도 개발
// 틀을 맞추고 이거에 맞게 개발 -> 이거 중요한 듯 !!

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public  void afterEach(){
        //test 끝낼때마다 새롭게 초기화 해줄 부분 추가
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("mino");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = "+(result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 복붙한거 shift+f6으로 rename 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();
        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(repository.findAll().size());
        assertThat(result).isEqualTo(repository.findAll());
    }
}
