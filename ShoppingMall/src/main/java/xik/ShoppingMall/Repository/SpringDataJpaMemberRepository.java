package xik.ShoppingMall.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xik.ShoppingMall.Domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}