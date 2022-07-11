package xik.ShoppingMall.Discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Order.Order;
import xik.ShoppingMall.Repository.MemberRepository;
import xik.ShoppingMall.Service.MemberServiceInterface;
import xik.ShoppingMall.Service.OrderService;
import xik.ShoppingMall.SpringConfig;

@SpringBootTest
@Transactional
class RateDiscountPolicyTest {

//    @Autowired
//    MemberServiceInterface memberService;
//
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    DiscountPolicy discountPolicy;

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

    MemberServiceInterface memberService = applicationContext.getBean("memberService", MemberServiceInterface.class);

    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

    @Test
    @DisplayName("VIP는 10퍼센트 할인이 들어간다.")
    void discount() {
        //given
        Member member = new Member();
        member.setName("hooper");
        member.setPhoneNumber("013440");
        member.setGrade(Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(member.getId(), "itemB", 30000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(3000);
        System.out.println("Order: "+order);
    }
}