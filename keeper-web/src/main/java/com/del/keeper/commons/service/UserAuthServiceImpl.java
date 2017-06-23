package com.del.keeper.commons.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.del.keeper.commons.dao.UserAuthDao;
import com.del.keeper.commons.entity.UserAuth;
import com.del.keeper.core.exception.ServiceException;

/**
 * 用户认证管理
 *
 * @author xie
 */
@Service
@Transactional(readOnly = true)
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    private UserAuthDao userAuthDao;

    @Override
    public UserAuth findUserAuthById(Integer id) throws ServiceException {
        return (UserAuth) userAuthDao.selectByPrimaryKey(id);
    }

    public UserAuth findUserAuthByParam(UserAuth userAuth) throws ServiceException {
        List<UserAuth> userAuthList = userAuthDao.selectListByPrimaryKeySelective(userAuth);
        if (userAuthList.size() == 0) {
            return null;
        }
        if (userAuthList.size() != 1) {
            throw new ServiceException("参数查询对象不能唯一!");
        }
        return userAuthList.get(0);
    }

    @Override
    public List<UserAuth> findListAuthByParam(UserAuth userAuth) throws ServiceException {
        return userAuthDao.selectListByPrimaryKeySelective(userAuth);
    }

    @Transactional(readOnly = false, rollbackFor = ServiceException.class)
    @Override
    public void addUserAuth(UserAuth userAuth) throws ServiceException {
        userAuthDao.insertSelective(userAuth);
    }

    @Transactional(readOnly = false, rollbackFor = ServiceException.class)
    @Override
    public void changeUserAuth(UserAuth userAuth) throws ServiceException {
        userAuthDao.updateByPrimaryKeySelective(userAuth);
    }

    @Transactional(readOnly = false, rollbackFor = ServiceException.class)
    @Override
    public void removeUserAuthById(Long id) throws ServiceException {
        userAuthDao.deleteByPrimaryKey(id);
    }
}
