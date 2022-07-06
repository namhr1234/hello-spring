package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

 //   MemberService memberService = new MemberService();
    MemberService memberService;
 //   MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //DI : Dependency Injection
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
/*
테스트는 한글로 해도 된다잉
 */
    @Test
    void 회원가입() {
        //given 먼가가 주어졌을 떄
        Member member = new Member();
        member.setName("hello");
        //when 이게 실행됬을 때
        Long saveID = memberService.join(member);
        //then 결과가 이게 나와야 된다
        Member findMember = memberService.findOne(saveID).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미존재하는 회원 입니다.");
   /*     try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){

        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}