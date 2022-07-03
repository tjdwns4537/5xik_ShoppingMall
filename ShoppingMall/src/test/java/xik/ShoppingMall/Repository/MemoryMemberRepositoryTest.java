package xik.ShoppingMall.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import xik.ShoppingMall.Domain.Member;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // Test 후 해당 테스트의 데이터가 남지 않게 지워주기위한 함수
    // 역할 : Test 끝날때마다 afterEach 가 실행되어 memberRepository를 지워줌
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("sungjun");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        //Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("parksungjun");
        member2.setName("july");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member result = memberRepository.findByName("parksungjun").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByPhoneNum() {
        Member member1 = new Member();
        member1.setPhoneNumber("010-7472-4537");
        member1.setName("parksungjun");

        memberRepository.save(member1);

        Member result = memberRepository.findByPhoneNumber("010-7472-4537").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("parksungjun");
        member2.setName("july");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }



}
