package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
public class OrderItem {

    public OrderItem() {

    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDERITEM_ID")
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @Getter
    @Setter
    @Column(name="ORDER_PRICE")
    private Integer OrderPrice; //Item 의 price

    @Getter
    @Column(name="ORDER_COUNT")
    private Integer Count; // Item의 재고

    public Item setCount(Integer count, Item item) {
        item.setStockQuantity(item.getStockQuantity()-count);
        return item;
    }

}
