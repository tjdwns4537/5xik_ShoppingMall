package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;

public class Order {
    @Getter
    @Setter
    @Column(name="MEMBER_ID")
    private Long memberId;

    @Getter
    @Setter
    @Transient
    private int discountPrice;

    public Order(Long memberId, int discountPrice) {
        this.memberId = memberId;
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
