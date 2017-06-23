package com.del.keeper.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.del.keeper.commons.entity.User;
import com.del.keeper.core.dao.CustomBaseDao;

/**
 * 用户数据访问
 */
@Repository
public class UserDao extends CustomBaseDao {
    public UserDao() {
        this.NAMESPASE = "com.del.keeper.commons.mepper.UserMapper";
    }

    public List<User> selectListByPrimaryKeySelective(Object record) {
        return this.getSqlSession().selectList(this.NAMESPASE + ".selectListByPrimaryKeySelective", record);
    }
}
