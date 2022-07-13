package xik.ShoppingMall.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Discount.DiscountPolicy;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Order.Order;
import xik.ShoppingMall.Repository.MemberRepository;

import java.util.Optional;

@Transactional
@Component
public class OrderServiceImp implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImp(MemberRepository memberRepository, @Qualifier("RateDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long MemberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByid(MemberId).get();
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(MemberId, itemName, itemPrice, discountPrice);
    }
}
