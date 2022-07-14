package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderItem {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDERITEM_ID")
    private Long id;

    @Getter
    @Setter
    @Column(name="ORDER_ID")
    private Long order_id;

    @Getter
    @Setter
    @Column(name="ITEM")
    private Long item_id;

    @Getter
    @Setter
    @Column(name="ORDER_PRICE")
    private Integer OrderPrice;

    @Getter
    @Setter
    @Column(name="ORDER_COUNT")
    private Integer Count;
}
