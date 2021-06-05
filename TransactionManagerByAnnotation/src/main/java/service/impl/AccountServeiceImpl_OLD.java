package service.impl;

import dao.AccountDao;
import domain.Account;
import service.AccountService;
import utils.TransactionManager;

import java.util.List;

public class AccountServeiceImpl_OLD implements AccountService {
    private AccountDao dao;
    private TransactionManager ts;

    public void setTs(TransactionManager ts) {
        this.ts = ts;
    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }


    public List<Account> findAll() {

        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            List<Account> list = dao.findAll();
            //3.提交事务
            ts.CommitTransaction();
            //4.返回结果
            return list;
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }

    }

    public Account findAccountById(Integer Accountid) {
        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            Account account = dao.findAccountById(Accountid);
            //3.提交事务
            ts.CommitTransaction();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }
    }

    public void saveAccount(Account account) {
        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            dao.saveAccount(account);
            //3.提交事务
            ts.CommitTransaction();
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }
    }

    public void updateAccount(Account account) {
        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            dao.updateAccount(account);
            //3.提交事务
            ts.CommitTransaction();
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }

    }

    public void deleteAccount(Integer Accountid) {
        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            dao.deleteAccount(Accountid);
            //3.提交事务
            ts.CommitTransaction();
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }

    }

    public void transfer(String sourceName, String targtName, Float money) {
        try {
            //1.开启事务
            ts.BeginTransaction();
            //2.执行操作
            //1.查询转出用户
            Account sourceAccount = dao.findAccountByName(sourceName);
            //2.查询转入用户
            Account targtAccount = dao.findAccountByName(targtName);
            //3.转出金钱
            sourceAccount.setMoney(sourceAccount.getMoney()-money);
            //4.转入金钱
            targtAccount.setMoney(targtAccount.getMoney()+money);
            //5.更新转出用户
            dao.updateAccount(sourceAccount);
            //6.更新被转入用户
            dao.updateAccount(targtAccount);
            //3.提交事务
            ts.CommitTransaction();
        } catch (Exception e) {
            //回滚操作
            ts.RollBack();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            ts.CloseConnection();
        }

    }
}
