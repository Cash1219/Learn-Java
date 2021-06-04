package utils;

/**
 * 日志类，用于管理事物
 */
public class logger {
    private TransactionManager tm = new TransactionManager();

    public void setTm(TransactionManager tm) {
        this.tm = tm;
    }

    public void begin(){
        tm.BeginTransaction();
        System.out.println("准备交易");
    }
    public void after(){
        tm.CommitTransaction();
        System.out.println("开始交易");
    }
    public void exception(){
        tm.RollBack();
        System.out.println("交易有误，已回滚");
    }
    public void over() {
        tm.CloseConnection();
        System.out.println("交易结束");
    }
}
