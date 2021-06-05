package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import utils.ConnectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@PropertySource("classpath:jdbc.properties") //用于指定properties文件的位置
public class jdbcConfiguration {

    @Value("${jdbc.driver}")
    private String Driver;
    @Value("${jdbc.url}")
    private String Url;
    @Value("${jdbc.user}")
    private String User;
    @Value("${jdbc.password}")
    private String Password;

    @Bean(name = "conn")
        public ConnectionUtils createConUtils(@Qualifier("ds1")DataSource ds, ThreadLocal<Connection> tl){
        return new ConnectionUtils(tl,ds);
    }
    @Bean(name = "tl")
        public ThreadLocal<Connection> createThreadLocal(){
        return new ThreadLocal<Connection>();
    }

    @Bean(name = "QueryRunner")
    public QueryRunner createQueryRunner(@Qualifier("ds1") DataSource ds) { //有2个相近的ds返回方法，系统进退两难
        return new QueryRunner(ds);                                           //可以使用qualifier指定想用的bean
    }                                                                           //这种情况下不需要autowired自动注入就能用qualifier
    @Bean(name = "ds1")
    public DataSource createDataSource()  {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(Driver);
            ds.setJdbcUrl(Url);
            ds.setUser(User);
            ds.setPassword(Password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean(name = "ds2")
    public DataSource createDataSource2()  {
        ComboPooledDataSource ds2 = new ComboPooledDataSource();
        try {
            ds2.setDriverClass(Driver);
            ds2.setJdbcUrl(Url);
            ds2.setUser(User);
            ds2.setPassword(Password);
            return ds2;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
