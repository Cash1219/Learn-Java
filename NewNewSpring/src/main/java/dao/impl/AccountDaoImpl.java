package dao.impl;

import dao.AccountDao;
import domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.ConnectionUtils;

import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Account findAccountById(Integer Accountid) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),Accountid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteAccount(Integer Accountid) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id = ?",Accountid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Account findAccountByName(String Accountname) {
        try {
            List<Account> list = runner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?", new BeanListHandler<Account>(Account.class), Accountname);
            if( list.size() == 0 || list == null){
                return null;
            }
            if( list.size()>1 ){
                throw new RuntimeException("数据有误，结果集不唯一");
            }
            return list.get(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
