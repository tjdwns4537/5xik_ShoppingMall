package xik.ShoppingMall.Service;

import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServiceImp implements MemberServiceInterface{

    private MemberRepository memberRepository;

    // 외부에서 리포지토리를 넣어줄 수 있게끔 직접 nw 하는게 아닌 생성자를 이용해서 만들어준다.
    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Override
    public Long join(Member member) {
        // 휴대폰 번호 중복 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 가입된 휴대폰 번호입니다.");
        });
    }

    @Override
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
