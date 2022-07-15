package xik.ShoppingMall.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Repository.MemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceImpIntegrationTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberServiceInterface memberService;

    @Test
    @Commit
    void join() {
        //given
        Member member = new Member();
        member.setName("parksungjun");
        member.setPhoneNumber("010-7472-4537");
        member.setGrade(Grade.BASIC);

        //when
        Long saveId = memberService.join(member);

        //then
        Member result = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void duplicate_Test() {
        //given
        Member mem1 = new Member();
        Member mem2 = new Member();

        mem1.setPhoneNumber("010-7472-4537");
        mem2.setPhoneNumber("010-7472-4537");

        //when
        memberService.join(mem1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
        assertThat(e.getMessage()).isEqualTo("이미 가입된 휴대폰 번호입니다.");
    }

    @Test
    void findMember() {

    }

    @Test
    void findOne() {

    }
}