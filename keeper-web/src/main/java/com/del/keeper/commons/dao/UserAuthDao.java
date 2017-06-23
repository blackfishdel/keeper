package com.del.keeper.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.del.keeper.commons.entity.UserAuth;
import com.del.keeper.core.dao.CustomBaseDao;

@Repository
public class UserAuthDao extends CustomBaseDao {

    public UserAuthDao() {
        this.NAMESPASE = "com.del.keeper.commons.mepper.UserAuthMapper";
    }

    public List<UserAuth> selectListByPrimaryKeySelective(Object record) {
        return this.getSqlSession().selectList(this.NAMESPASE + ".selectListByPrimaryKeySelective", record);
    }
}
