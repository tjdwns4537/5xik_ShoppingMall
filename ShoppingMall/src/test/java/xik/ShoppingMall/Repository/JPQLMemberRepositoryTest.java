package xik.ShoppingMall.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.AutoSpringConfig;
import xik.ShoppingMall.Domain.Member;
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
    void insertData() {
        //tx.begin();
        try{
            Member member = new Member();
            member.setName("sungjun");
            member.setPhonenumber("010");

            repository.save(member);
            Member result =  repository.findByname(member.getName()).get();

            Assertions.assertThat(result).isEqualTo(member);
            //tx.commit();

        } catch (Exception e){
            //tx.rollback();
        } finally {
            //repository.emClose();
        }
        //ac.close();
    }
}