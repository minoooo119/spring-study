package hello.hello_spring.domain;

import jakarta.persistence.*;

//JPA -> ORM 객체와 릴레이션을 매핑한다는 것
@Entity
public class Member {

    //DB 에서 자동으로 설정해주는 경우
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username") 이런식으로 매핑을 해줄 수 있다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
