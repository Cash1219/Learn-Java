package ui;

import config.springConfiguration;
import domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import service.AccountService;

import java.util.List;

@Controller("Demo")
public class DemoConfiguration {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(springConfiguration.class);
        AccountService service = (AccountService)context.getBean("serviceDao");
        List<Account> list = service.findAll();
        System.out.println(list);

//        QueryRunner qr1 = (QueryRunner) context.getBean("QueryRunner");
//        QueryRunner qr2 = (QueryRunner) context.getBean("QueryRunner");
//        System.out.println(qr1==qr2); //单例
    }

}
