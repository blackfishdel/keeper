package com.del.keeper.commons.service;

import com.del.keeper.commons.entity.UserAuthLog;
import com.del.keeper.core.exception.ServiceException;

public interface UserAuthLogService {

    void addUserAuthLog(UserAuthLog userAuthLog) throws ServiceException;
}
