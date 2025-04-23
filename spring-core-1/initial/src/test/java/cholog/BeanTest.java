package cholog;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import cholog.bean.AutowiredBean;
import cholog.bean.SpringBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

public class BeanTest {

    @Test
    void registerBean() {
        ApplicationContext context = getApplicationContext();
        SpringBean springBean = context.getBean("springBean", SpringBean.class);
        assertThat(springBean).isNotNull();
    }

    @Test
    void autowiredBean() {
        ApplicationContext context = getApplicationContext();
        AutowiredBean autowiredBean = context.getBean("autowiredBean", AutowiredBean.class);
        assertThat(autowiredBean.sayHello()).isEqualTo("Hello");
        //SpringBean을 @Autowired로 컨테이너에서 의존성 주입을 해주기 때문에 new SpringBean 객체를 직접 생성해줄 필요가 없다.
    }
}
