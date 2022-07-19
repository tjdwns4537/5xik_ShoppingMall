package xik.ShoppingMall.Repository;

import xik.ShoppingMall.Domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            membertest.setName("parksungjun");
            membertest.setPhonenumber("01074724537");
            membertest.setGrade(Grade.VIP);
            membertest.setZipcode("거제도");
            em.persist(membertest);

//            Outer outer = new Outer();
//            outer.setName("morphin");
//            outer.setGardigan("2022SS");
//            outer.setPrice(30000);
//            outer.setStockQuantity(5);

            Item item = new Item();
            item.setItemList("A item", 30000,5);

            Item item2 = new Item();
            item2.setItemList("B item", 40000,4);

            Item item3 = new Item();
            item3.setItemList("C item", 50000,3);

            Item item4 = new Item();
            item4.setItemList("D item", 10000,6);

            Item item5 = new Item();
            item5.setItemList("E item", 20000,7);

            em.persist(item);
            em.persist(item2);
            em.persist(item3);
            em.persist(item4);
            em.persist(item5);

            Order order = new Order();

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrder(order);
            item = orderItem.setCount(1,item);

            em.persist(order);
            em.persist(orderItem);

            System.out.println("할인된 가격 : "+order.getDiscountPrice());
            System.out.println("주문된 상품 : "+orderItem.getItem().getName());
            System.out.println("상품 재고 : "+item.getStockQuantity());
            System.out.println("주문 고객 : " + membertest.getName());

            em.flush(); // 영속성 컨텍스트에 있는것 다 날리고
            em.clear(); // 영속성 컨텍스트 제거

//            Order findOrder = em.find(Order.class, order.getOrderItems());
//
//            List<OrderItem> orderItems = findOrder.getOrderItems();
//            for(OrderItem i : orderItems){
//                System.out.println("OrderItem : " + i.getOrderPrice());
//            }

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}
