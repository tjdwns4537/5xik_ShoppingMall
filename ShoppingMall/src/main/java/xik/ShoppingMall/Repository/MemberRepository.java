package xik.ShoppingMall.Repository;

import xik.ShoppingMall.Domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 저장소에 저장

    Optional<Member> findById(Long id); // 저장소에서 id 찾기
    Optional<Member> findByName(String name); // 저장소에서 name 찾기
    Optional<Member> findByPhoneNumber(String phoneNumber); // 저장소에서 name 찾기

    List<Member> findAll(); // 저장소에서 전체목록 찾기
}
