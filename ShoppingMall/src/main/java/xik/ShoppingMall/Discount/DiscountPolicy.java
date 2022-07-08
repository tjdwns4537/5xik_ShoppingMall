package xik.ShoppingMall.Discount;

import xik.ShoppingMall.Domain.Member;

import java.util.Optional;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
