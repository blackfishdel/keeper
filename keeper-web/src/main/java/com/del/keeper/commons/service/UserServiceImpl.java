package com.del.keeper.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.del.keeper.commons.dao.UserDao;
import com.del.keeper.commons.entity.User;
import com.del.keeper.core.exception.ServiceException;

/**
 * 用户数据管理服务
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * id查询对象
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public User findUserById(Integer id) throws ServiceException {
        return (User) userDao.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByParam(User user) throws ServiceException {
        List<User> userList = userDao.selectListByPrimaryKeySelective(user);
        if (userList.size() == 0) {
            return null;
        }
        if (userList.size() != 1) {
            throw new ServiceException("参数查询对象不能唯一!");
        }
        return userList.get(0);
    }

    @Override
    public List<User> finListByParam(User user) throws ServiceException {
        return userDao.selectListByPrimaryKeySelective(user);
    }

    /**
     * 选择添加对象
     *
     * @param user
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void addUser(User user) throws ServiceException {
        userDao.insertSelective(user);
    }

    /**
     * 选择更新对象
     *
     * @param user
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void changeUser(User user) throws ServiceException {
        userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * id删除user
     *
     * @param id
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void removeUserById(Integer id) throws ServiceException {
        userDao.deleteByPrimaryKey(id);
    }
}
