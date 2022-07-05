package xik.ShoppingMall.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository memberRepository;

    // 외부에서 리포지토리를 넣어줄 수 있게끔 직접 nw 하는게 아닌 생성자를 이용해서 만들어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 휴대폰 번호 중복 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getPhoneNumber());
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
