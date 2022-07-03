package xik.ShoppingMall.Repository;

import org.junit.jupiter.api.AfterEach;
import org.springframework.stereotype.Repository;
import xik.ShoppingMall.Domain.Member;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 임시 저장히기위한 변수
    private static long sequence = 0L; // store 의 key 값을 생성해주는 부분

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findByPhoneNumber(String phoneNumber) {
        return store.values().stream()
                .filter(member -> member.getPhoneNumber().equals(phoneNumber))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
