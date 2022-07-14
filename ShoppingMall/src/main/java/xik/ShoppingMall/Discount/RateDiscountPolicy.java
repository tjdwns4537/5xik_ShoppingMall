package xik.ShoppingMall.Discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    private int rateDisocunt = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * rateDisocunt / 100;
        } else{
            return 0;
        }
    }
}
