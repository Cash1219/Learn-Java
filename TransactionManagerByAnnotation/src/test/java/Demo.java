import config.springConfiguration;
import domain.Account;
import factory.beanFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AccountService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //打上这个Spring-test的包，会改变原有的runner，使得测试兼容spring框架
//@ContextConfiguration(classes = springConfiguration.class) //选择spring开发方式 location：加入classpath关键字表示在类路径下
                                                                            //classes：注解类位置
//如果spring版本是5.x，junit的版本需要4.12及其以上
@ContextConfiguration(classes = springConfiguration.class)
public class Demo {
//    beanFactory factory = new beanFactory();
//    @Qualifier("ProxyService")
    @Autowired
//    private AccountService as = factory.getAccountService(); 先前的工 厂代理控制事物
    ApplicationContext ac = new AnnotationConfigApplicationContext(springConfiguration.class);
    AccountService service = (AccountService) ac.getBean("serviceDao");
    @Test
    public void test() {
        service.transfer("aaa","bbb",100f);
    }
}
