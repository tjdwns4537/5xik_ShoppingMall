package xik.ShoppingMall.BeanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xik.ShoppingMall.AutoSpringConfig;
import xik.ShoppingMall.SpringConfig;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoSpringConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findBean() {
        String[] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String i : beanDefinitionName) {
            Object bean = ac.getBean(i);
            System.out.println("name = " + i + "object" + bean);
        }
    }

    @Test
    @DisplayName("application 빈 출력하기")
    void findApplication() {
        String[] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String i : beanDefinitionName) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(i);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(i);
                System.out.println("name = " + i + "object" + bean);
            }
        }
    }

}
