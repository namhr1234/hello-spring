package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
    repository.clearStore(); //순서상관없이 한 메소드 실행할때 마다 공용저장소 지워준다. 서로 의존관계 없이 설계되어야한다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result); //맞나 아닌지 확인하는게 있다.
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }
}
