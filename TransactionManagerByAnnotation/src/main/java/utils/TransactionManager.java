package utils;


import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 和事物管理相关的工具类，包含开启事务，提交事务，回滚事物和释放连接
 */
@Component("ts")
@Aspect
public class TransactionManager {
    @Autowired
    private ConnectionUtils conUtils;
    @Pointcut("execution(* *..*.service.impl.*.*(..))")
    private void pt(){}

//    public void setConUtils(ConnectionUtils conUtils) {
//        this.conUtils = conUtils;
//    }

    @Before("pt()")
    public void BeginTransaction() {
        try {
            conUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @AfterReturning("pt()")
    public void CommitTransaction(){
        try {
            conUtils.getThreadConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @AfterThrowing("pt()")
    public void RollBack(){
        try {
            conUtils.getThreadConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @After("pt()")
    public void CloseConnection(){
        try {
            conUtils.getThreadConnection().close();
            conUtils.RemoveConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
