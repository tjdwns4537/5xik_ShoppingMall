package xik.ShoppingMall.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Discount.DiscountPolicy;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Domain.Order;
import xik.ShoppingMall.Repository.MemberRepository;

@Transactional
@Component
public class OrderServiceImp implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long MemberId,Integer price) {
        Member member = memberRepository.findByid(MemberId).get();
        int discountPrice = discountPolicy.discount(member,price);

        return new Order(discountPrice);
    }
}
