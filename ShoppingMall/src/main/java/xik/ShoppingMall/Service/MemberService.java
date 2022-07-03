package xik.ShoppingMall.Service;

import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Repository.MemberRepository;
import xik.ShoppingMall.Repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
        // 휴대폰 번호 중복 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 가입된 휴대폰 번호입니다.");
        });
    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
