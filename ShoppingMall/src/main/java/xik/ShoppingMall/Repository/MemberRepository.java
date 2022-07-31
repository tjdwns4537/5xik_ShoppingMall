package xik.ShoppingMall.Repository;

import xik.ShoppingMall.Domain.Member;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 저장소에 저장

    EntityTransaction getTx();

    Optional<Member> findByid(Long id); // 저장소에서 id 찾기
    Optional<Member> findByname(String name); // 저장소에서 name 찾기
    Optional<Member> findByphonenumber(String phoneNumber); // 저장소에서 name 찾기

    List<Member> findAll(); // 저장소에서 전체목록 찾기
}
