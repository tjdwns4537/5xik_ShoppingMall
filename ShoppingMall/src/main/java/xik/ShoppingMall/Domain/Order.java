package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order {
    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    // 연관관계 주인
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Getter
    @Setter
    @Transient
    private int discountPrice;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private OrderStatus status;

    @Getter
    @Setter
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Order() {

    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + member.getId() +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
