package xik.ShoppingMall.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Discount.DiscountPolicy;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Domain.Order;
import xik.ShoppingMall.Repository.MemberRepository;

@SpringBootTest
@Transactional
public class OrderServiceImpTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberServiceInterface memberService;

    @Autowired
    DiscountPolicy discountPolicy;

    @Autowired
    OrderService orderService;

    @Test
    void createOrder(){
        //given
        Member member1 = new Member();
        member1.setName("ParkJJJ");
        member1.setPhonenumber("01099992111");
//        member1.setGrade(Grade.BASIC);
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("GOOMMOO");
        member2.setPhonenumber("01133334444");
//        member2.setGrade(Grade.VIP);
        memberService.join(member2);

        //when
        Order order = orderService.createOrder(member2.getId(),30000);
        // 30000 자리에 ItemPrice 들어가야함

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
