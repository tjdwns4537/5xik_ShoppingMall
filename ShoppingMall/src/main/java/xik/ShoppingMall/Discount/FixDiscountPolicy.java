package xik.ShoppingMall.Discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;

import java.util.Optional;

@Component
@Qualifier("FixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixMount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixMount;
        }
        else{
            return 0;
        }
    }
}
