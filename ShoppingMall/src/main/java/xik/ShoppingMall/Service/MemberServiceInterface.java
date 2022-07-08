package xik.ShoppingMall.Service;

import xik.ShoppingMall.Domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberServiceInterface {

    // 회원가입
    Long join(Member member);

    List<Member> findMember();

    Optional<Member> findOne(Long memberId);

    void validateDuplicateMember(Member member);
}
