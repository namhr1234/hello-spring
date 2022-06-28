package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member memeber);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); //java 8 Optional
    List<Member> findAll();
}
