package xik.ShoppingMall.Repository;

import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Domain.Order;
import xik.ShoppingMall.Domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaRepositoryTest {

    public static void main(String[] args) {
        // 엔티티 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("5xik");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 객체 생성
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member membertest = new Member();
            membertest.setName("parksung");
            membertest.setPhoneNumber("01074724537");
            em.persist(membertest);

            Order order = new Order();
            order.setItemname("반팔티");
            order.setMember(membertest);
            em.persist(order);

            Order order2 = new Order();
            order2.setItemname("긴팔티");
            order2.setMember(membertest);
            em.persist(order2);

            //order1,order2 를 각각 persist 해줘야 회원의 상품 주문을 뭘 했는지 알 수 있다.

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderPrice(30000);
            orderItem.setCount(3);
            orderItem.setOrder(order);
            em.persist(orderItem);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setOrderPrice(50000);
            orderItem2.setCount(1);
            orderItem2.setOrder(order);
            em.persist(orderItem2);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, membertest.getId());
            Order findOrder = em.find(Order.class, order.getOrderItems());

            List<Order> orders = findMember.getOrders();
            for(Order i : orders){
                System.out.println("Member : " + i.getItemname());
            }

            List<OrderItem> orderItems = findOrder.getOrderItems();
            for(OrderItem i : orderItems){
                System.out.println("OrderItem : " + i.getOrderPrice());
            }

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}
