package xik.ShoppingMall.Order;

import lombok.Getter;
import lombok.Setter;

public class Order {
    @Getter
    @Setter
    private Long memberId;

    @Getter
    @Setter
    private String itemName;

    @Getter
    @Setter
    private int itemPrice;

    @Getter
    @Setter
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        itemPrice -= discountPrice;
        return itemPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
