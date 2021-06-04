package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，从数据源中取出一个连接，实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private DataSource ds;

    public ConnectionUtils(ThreadLocal<Connection> tl, DataSource ds) {
        this.tl = tl;
        this.ds = ds;
    }


    public void setTl(ThreadLocal<Connection> tl) {
        this.tl = tl;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection() {
        //1.先从ThreadLocal上获取
        //2.判断当前线程上是否有连接
        try {
            Connection con = tl.get();
            if (con == null) {
            //3.从数据源中获取一个连接并存入ThreadLocal中
                con = ds.getConnection();
                tl.set(con);
            }
            //4.返回当前线程上的连接
            return con;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void RemoveConnection(){
        tl.remove();
    }
}
