package xik.ShoppingMall.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ORDERS")
public class Order {
    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Getter
    @Setter
    @Column(name = "ITEM_NAME")
    private String itemname;

    public void setMember(Member member) {
        this.member = member;

        member.getOrders().add(this);
    }

    // 연관관계 주인
    @Getter
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

    public Order(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Order() {

    }


    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + member.getId() +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
