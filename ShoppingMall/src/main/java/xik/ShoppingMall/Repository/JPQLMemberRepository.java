package xik.ShoppingMall.Repository;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xik.ShoppingMall.Domain.Address;
import xik.ShoppingMall.Domain.Member;
import xik.ShoppingMall.Domain.WorkPeriod;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Transactional
public class JPQLMemberRepository implements MemberRepository{

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @Autowired
    public JPQLMemberRepository(EntityManagerFactory emf, EntityManager em, EntityTransaction tx) {
        this.emf = emf;
        this.em = em;
        this.tx = tx;
    }

    public EntityTransaction getTx() {
        return tx;
    }

    @Override
    public Member save(Member member) {
        tx.begin();
        try {
            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            //em.close();
        }
        //mf.close();
        return member;
    }

    public void emClose() {
        em.close();
    }

    @Override
    public Optional<Member> findByid(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByname(String name) {
        List<Member> result = em.createQuery(
                "SELECT m FROM Member m WHERE m.name = :name",
                Member.class
        ).setParameter("name", name).getResultList();

        System.out.println("--------------------------------");
        for (Member i : result) {
            System.out.println("member:" + i);
        }
        System.out.println("--------------------------------");

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByphonenumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("5xik");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        try{
//            Member member = new Member();
//            member.setName("sungjun");
//            member.setPhonenumber("010");
//
//            em.persist(member);
//            List<Member> result = em.createQuery(
//                    "SELECT m FROM Member m WHERE m.name = :name",
//                    Member.class
//            ).setParameter("name", member.getName()).getResultList();
//
//            System.out.println("--------------------------------");
//            for (Member i : result) {
//                System.out.println("member:" + i);
//            }
//            System.out.println("--------------------------------");
//            tx.commit();
//
//        } catch (Exception e){
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
}
