package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.Account;
import service.AccountService;
import utils.TransactionManager;

import java.util.List;

public class AccountServeiceImpl implements AccountService {
    private AccountDao dao;
    private TransactionManager ts;

    public void setTs(TransactionManager ts) {
        this.ts = ts;
    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
    }


    public List<Account> findAll() {
        return dao.findAll();
    }

    public Account findAccountById(Integer Accountid) {
        return dao.findAccountById(Accountid);
    }

    public void saveAccount(Account account) {
        dao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        dao.updateAccount(account);
    }

    public void deleteAccount(Integer Accountid) {
        dao.deleteAccount(Accountid);
    }

    public void transfer(String sourceName, String targtName, Float money) {
        Account sourceAccount = dao.findAccountByName(sourceName);
        Account targtAccount = dao.findAccountByName(targtName);
        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        targtAccount.setMoney(targtAccount.getMoney()+money);
        dao.updateAccount(sourceAccount);
        dao.updateAccount(targtAccount);
    }
}
