package xik.ShoppingMall.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Repository.MemoryMemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceImpTest {

    MemoryMemberRepository memberRepository;
    MemberServiceImp memberServiceImp;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        //memberServiceImp = new MemberServiceImp(memberRepository);
        memberServiceImp = new MemberServiceImp(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("parksungjun");

        //when
        Long saveId = memberServiceImp.join(member);

        //then
        Member result = memberServiceImp.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void duplicate_Test() {
        //given
        Member mem1 = new Member();
        Member mem2 = new Member();

//        mem1.setPhoneNumber("010-7472-4537");
//        mem2.setPhoneNumber("010-7472-4537");

        //when
        memberServiceImp.join(mem1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberServiceImp.join(mem2));
        assertThat(e.getMessage()).isEqualTo("이미 가입된 휴대폰 번호입니다.");
    }

    @Test
    void findMember() {

    }

    @Test
    void findOne() {

    }
}