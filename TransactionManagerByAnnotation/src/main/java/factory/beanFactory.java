package factory;

import domain.Account;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import service.AccountService;
import service.impl.AccountServeiceImpl;
import utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;


public class beanFactory {
    private AccountService accountService;
    private TransactionManager ts;

    public final void setTx(TransactionManager ts) {
        this.ts = ts;
    }
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    public AccountService getAccountService(){
        return (AccountService) Proxy.newProxyInstance(AccountServeiceImpl.class.getClassLoader(),
                AccountServeiceImpl.class.getInterfaces(),
                new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                try {
                    //1.开启事务
                    ts.BeginTransaction();
                    //2.执行操作
                    returnValue = method.invoke(accountService,args);
                    //3.提交事务
                    ts.CommitTransaction();
                    //4.返回结果
                } catch (Exception e) {
                    //回滚操作
                    ts.RollBack();
                    throw new RuntimeException(e);
                } finally {
                    //释放连接
                    ts.CloseConnection();
                }
                return returnValue;
            }
        });

    }
}
