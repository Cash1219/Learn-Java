package utils;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * 和事物管理相关的工具类，包含开启事务，提交事务，回滚事物和释放连接
 */
public class TransactionManager {
    private ConnectionUtils conUtils;

    public void setConUtils(ConnectionUtils conUtils) {
        this.conUtils = conUtils;
    }

    public void BeginTransaction() {
        try {
            conUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void CommitTransaction(){
        try {
            conUtils.getThreadConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void RollBack(){
        try {
            conUtils.getThreadConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void CloseConnection(){
        try {
            conUtils.getThreadConnection().close();
            conUtils.RemoveConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
