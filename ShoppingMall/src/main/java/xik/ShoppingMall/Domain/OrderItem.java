package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

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
    private Integer OrderPrice;

    @Getter
    @Setter
    @Column(name="ORDER_COUNT")
    private Integer Count;
}
