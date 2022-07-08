package xik.ShoppingMall.Discount;

import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;

import java.util.Optional;

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
