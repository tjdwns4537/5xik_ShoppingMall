package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Item {

    public Item() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ID")
    private Long id;

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
    private Integer stockQuantity;

    public void setItemList(String itemName, Integer price, Integer stockQuantity) {
        this.name = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
