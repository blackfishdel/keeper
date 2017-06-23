package com.del.keeper.commons.service;

import java.util.List;

import com.del.keeper.commons.entity.UserAuth;
import com.del.keeper.core.exception.ServiceException;

public interface UserAuthService {

    UserAuth findUserAuthById(Integer id) throws ServiceException;

    UserAuth findUserAuthByParam(UserAuth userAuth) throws ServiceException;

    List<UserAuth> findListAuthByParam(UserAuth userAuth) throws ServiceException;

    void addUserAuth(UserAuth userAuth) throws ServiceException;

    void changeUserAuth(UserAuth userAuth) throws ServiceException;

    void removeUserAuthById(Long id) throws ServiceException;
}
