package com.del.keeper.commons.service;

import com.del.keeper.commons.entity.AccountLog;
import com.del.keeper.core.exception.ServiceException;

/**
 * Created by xie
 */
public interface AccountLogService {

    AccountLog findAccountLogById(Integer id) throws ServiceException;

    void addAccountLog(AccountLog AccountLog) throws ServiceException;

    void changeAccountLog(AccountLog AccountLog) throws ServiceException;

    void removeAccountLogById(Integer id) throws ServiceException;
}
