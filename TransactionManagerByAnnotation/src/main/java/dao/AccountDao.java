package dao;

import domain.Account;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 查询单个
     * @param Accountid
     * @return
     */
    Account findAccountById(Integer Accountid);

    /**
     * 保存操作
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 修改操作
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除操作
     * @param Accountid
     */
    void deleteAccount(Integer Accountid);


    Account findAccountByName(String Accountname);
}
