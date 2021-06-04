package config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import dao.impl.AccountDaoImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;
import service.impl.AccountServeiceImpl;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 配置类，作用与applicationContext.xml一致
 * 新注解：Configuration
 *       作用：表明当前类是配置类
 *       ComponentScan
 *       作用：用于通过注解指定spring在创建容器时要扫描的包
 *       属性：basePackages和Value，作用一致。
 *       Bean
 *      作用：用于把当前方法的返回值作为Bean对象存入spring的ioc容器中
 *      属性：name 用于指定bean的id 默认值：当前方法名称
 *      如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *      查找的方式和AutoWired注解作用相同
 *
 */
@Configuration //如果该类作为AnnotationConfigApplicationContext的参数存在，则可以不写这行注释
//@ComponentScan({"dao","config"}) //扫描配置类，所有配置类必须有configuration注解才会被扫描到
@Import(jdbcConfiguration.class) //也可以用迫真导包

public class springConfiguration {
    @Bean(name = "serviceDao")
    public AccountServeiceImpl createAccountServiceImpl(AccountDaoImpl accountDao){
        AccountServeiceImpl serveice = new AccountServeiceImpl();
        serveice.setDao(accountDao);
        return serveice;
    }
    @Bean(name = "accountDao")
    public AccountDaoImpl createAccountDaoImpl(QueryRunner queryRunner){
        AccountDaoImpl dao = new AccountDaoImpl();
        dao.setRunner(queryRunner);
        return dao;
    }
}
