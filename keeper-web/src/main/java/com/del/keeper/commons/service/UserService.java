package com.del.keeper.commons.service;

import java.util.List;

import com.del.keeper.commons.entity.User;
import com.del.keeper.core.exception.ServiceException;

public interface UserService {

    User findUserById(Integer id) throws ServiceException;

    User findUserByParam(User user) throws ServiceException;

    List<User> finListByParam(User user) throws ServiceException;

    void addUser(User user) throws ServiceException;

    void changeUser(User user) throws ServiceException;

    void removeUserById(Integer id) throws ServiceException;
}
