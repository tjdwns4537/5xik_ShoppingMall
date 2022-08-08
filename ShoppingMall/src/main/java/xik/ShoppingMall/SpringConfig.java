package xik.ShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xik.ShoppingMall.Discount.DiscountPolicy;
import xik.ShoppingMall.Discount.RateDiscountPolicy;
import xik.ShoppingMall.Repository.JPQLMemberRepository;
import xik.ShoppingMall.Repository.MemberRepository;
import xik.ShoppingMall.Service.MemberServiceImp;
import xik.ShoppingMall.Service.MemberServiceInterface;
import xik.ShoppingMall.Service.OrderService;
import xik.ShoppingMall.Service.OrderServiceImp;

import javax.persistence.*;

@Configuration
public class SpringConfig {

//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public SpringConfig(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

//    @Bean
//    @Primary
//    public EntityManagerFactory getEnf() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("5xik");
//        return emf;
//    }
    //em을 빈등록 해준다.

    @Bean
    public EntityManagerFactory getEmf() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("5xik");
        return emf;
    }

    @Bean
    public EntityManager getEm() {
        EntityManagerFactory emf = getEmf();
        EntityManager em = emf.createEntityManager();
        return em;
    }

    @Bean
    public EntityTransaction getTx() {
        EntityManager em = getEm();
        EntityTransaction tx = em.getTransaction();
        return tx;
    }

    @Bean
    public MemberServiceInterface memberService() {
        return new MemberServiceImp(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JPQLMemberRepository(getEmf(),getEm(),getTx());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImp(memberRepository(), discountPolicy());
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // return new MemoryMemberRepository();
//        //return new JDBCMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//
//    }
}
