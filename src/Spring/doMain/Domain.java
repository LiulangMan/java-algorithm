package Spring.doMain;

import Spring.Control.Control;
import Spring.Factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Domain {


    public static void main(String[] args) {
        Control control = BeanFactory.getBean("userControl");
        control.queryUserByid(1);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:*.xml");
    }
}
