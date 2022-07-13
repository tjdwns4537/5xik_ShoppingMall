package xik.ShoppingMall.SingleTon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {
    @Test
    void FailSigleTon() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean("stateFulService", StateFulService.class);
        StateFulService stateFulService2 = ac.getBean("stateFulService", StateFulService.class);

        int price1 = stateFulService1.order("parkA", 10000);
        int price2 = stateFulService2.order("parkB", 20000);

        Assertions.assertThat(price1).isEqualTo(10000);
    }

    static class TestConfig{

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }
}

