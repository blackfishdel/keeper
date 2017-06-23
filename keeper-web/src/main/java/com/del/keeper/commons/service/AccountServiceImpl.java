package com.del.keeper.commons.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.del.keeper.commons.dao.AccountDao;
import com.del.keeper.commons.entity.Account;
import com.del.keeper.core.exception.ServiceException;

/**
 * Created by xie
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Account findAccountById(Integer id) throws ServiceException {
        return (Account) accountDao.selectByPrimaryKey(id);
    }

    @Override
    public Account findListByParam(Account Account) throws ServiceException {
        List<Account> AccountList = findListByParams(Account);
        if (AccountList.size() == 0) {
            return null;
        }
        if (AccountList.size() != 1) {
            throw new ServiceException("查询账户不能唯一！");
        }
        return AccountList.get(0);
    }

    @Override
    public List<Account> findListByParams(Account Account) throws ServiceException {
        return accountDao.selectListByPrimaryKeySelective(Account);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void addAccount(Account account) throws ServiceException {
        accountDao.insertSelective(account);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void changeAccount(Account Account) throws ServiceException {
        accountDao.updateByPrimaryKeySelective(Account);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void removeAccountById(Integer id) throws ServiceException {
        accountDao.deleteByPrimaryKey(id);
    }
}
