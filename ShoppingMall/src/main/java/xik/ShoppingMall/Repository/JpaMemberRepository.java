package xik.ShoppingMall.Repository;

import org.springframework.stereotype.Repository;
import xik.ShoppingMall.Domain.Grade;
import xik.ShoppingMall.Domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByid(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByname(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByphonenumber(String phoneNumber) {
        List<Member> result = em.createQuery("select m from Member m where m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findall() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
