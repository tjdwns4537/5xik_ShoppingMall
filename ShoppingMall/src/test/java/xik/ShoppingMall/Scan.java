package xik.ShoppingMall;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xik.ShoppingMall.Service.MemberServiceInterface;

public class Scan {
    @Test
    void ScanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoSpringConfig.class);
        MemberServiceInterface ms = ac.getBean(MemberServiceInterface.class);
        Assertions.assertThat(ms).isInstanceOf(MemberServiceInterface.class);
    }
}
