package xik.ShoppingMall.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import xik.ShoppingMall.Domain.Member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberServiceTest {
    MemberService memberService = new MemberService();

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("parksungjun");

        //when
        Long saveId = memberService.join(member);

        //then
        Member result = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void findMember() {

    }

    @Test
    void findOne() {

    }
}