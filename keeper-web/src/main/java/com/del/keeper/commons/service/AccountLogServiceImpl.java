package com.del.keeper.commons.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.del.keeper.commons.dao.AccountLogDao;
import com.del.keeper.commons.entity.AccountLog;
import com.del.keeper.core.exception.ServiceException;

/**
 * Created by xie
 */
@Service
@Transactional(readOnly = true)
public class AccountLogServiceImpl implements AccountLogService {

    @Resource
    private AccountLogDao accountLogDao;

    @Override
    public AccountLog findAccountLogById(Integer id) throws ServiceException {
        return (AccountLog) accountLogDao.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void addAccountLog(AccountLog accountLog) throws ServiceException {
        accountLogDao.insertSelective(accountLog);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void changeAccountLog(AccountLog accountLog) throws ServiceException {
        accountLogDao.updateByPrimaryKeySelective(accountLog);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void removeAccountLogById(Integer id) throws ServiceException {
        accountLogDao.deleteByPrimaryKey(id);
    }
}
