import config.springConfiguration;
import domain.Account;
import factory.beanFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AccountService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //打上这个Spring-test的包，会改变原有的runner，使得测试兼容spring框架
//@ContextConfiguration(classes = springConfiguration.class) //选择spring开发方式 location：加入classpath关键字表示在类路径下
                                                                            //classes：注解类位置
//如果spring版本是5.x，junit的版本需要4.12及其以上
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Demo {
    beanFactory factory = new beanFactory();
    @Qualifier("ProxyService")
    @Autowired
    private AccountService as = factory.getAccountService();
    @Test
    public void test() {
        as.transfer("aaa","bbb",100f);
    }
}
