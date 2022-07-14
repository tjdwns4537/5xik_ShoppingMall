package xik.ShoppingMall.Repository;

import xik.ShoppingMall.Domain.Member;

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
            membertest.setName("parksung");
            membertest.setPhoneNumber("01074724537");

            em.persist(membertest);
            //Member findMember1 = em.find(Member.class, 1L);

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}
