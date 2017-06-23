package com.del.keeper.commons.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.del.keeper.commons.dao.UserAuthLogDao;
import com.del.keeper.commons.entity.UserAuthLog;
import com.del.keeper.core.exception.ServiceException;

@Service
@Transactional(readOnly = true)
public class UserAuthLogServiceImpl implements UserAuthLogService {

    @Resource
    private UserAuthLogDao userAuthLogDao;

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void addUserAuthLog(UserAuthLog userAuthLog) throws ServiceException {
        userAuthLogDao.insert(userAuthLog);
    }
}
