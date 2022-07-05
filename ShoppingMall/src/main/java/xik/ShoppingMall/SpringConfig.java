package xik.ShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xik.ShoppingMall.Repository.JDBCMemberRepository;
import xik.ShoppingMall.Repository.JdbcTemplateMemberRepository;
import xik.ShoppingMall.Repository.MemberRepository;
import xik.ShoppingMall.Service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        //return new JDBCMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
