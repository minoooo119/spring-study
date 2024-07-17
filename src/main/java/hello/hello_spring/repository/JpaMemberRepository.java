package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // entity manager 로 관리를 한다.
    // 이건 자동으로 spring boot 에서 생성해준다. 그리고 injection 받으면 된다.
    // jpa 를 쓰려면 이 레포지토리에 em 을 주입받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        //이렇게 하면 알아서 다 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //이게 jqpl 이라는 query language  이다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
