package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ID")
    private Long item_id;

    @Getter
    @Setter
    @Column(name="ITEM_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="ITEM_PRICE")
    private Integer price;

    @Getter
    @Setter
    @Column(name="ITEM_STOCKQUANTITY")
    private Integer sotckQuantity;
}
