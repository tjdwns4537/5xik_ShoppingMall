package xik.ShoppingMall.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.AutoSpringConfig;
import xik.ShoppingMall.Domain.Address;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Domain.WorkPeriod;
import xik.ShoppingMall.SpringConfig;

import javax.persistence.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JPQLMemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    @Commit
    void insertData() {

        Member member = new Member();
        member.setName("test");
        member.setPhonenumber("01011");
        member.setAddress(new Address("city","street","10000"));
        member.setWorkPeriod(new WorkPeriod());

        Member member2 = new Member();
        member2.setName("test");
        member2.setPhonenumber("01011");
        member2.setAddress(new Address("city2","street2","20000"));

        member2.getAddress().setCity("geoje"); // 수정

        Member save = repository.save(member);
        Member save2 = repository.save(member2);
    }
}