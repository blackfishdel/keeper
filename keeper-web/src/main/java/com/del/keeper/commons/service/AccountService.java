package com.del.keeper.commons.service;

import java.util.List;

import com.del.keeper.commons.entity.Account;
import com.del.keeper.core.exception.ServiceException;

/**
 * Created by xie
 */
public interface AccountService {

    Account findAccountById(Integer id) throws ServiceException;

    Account findListByParam(Account account) throws ServiceException;

    List<Account> findListByParams(Account account) throws ServiceException;

    void addAccount(Account account) throws ServiceException;

    void changeAccount(Account account) throws ServiceException;

    void removeAccountById(Integer id) throws ServiceException;
}
