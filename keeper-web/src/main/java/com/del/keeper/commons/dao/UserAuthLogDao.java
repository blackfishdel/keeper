package com.del.keeper.commons.dao;

import org.springframework.stereotype.Repository;

import com.del.keeper.core.dao.CustomBaseDao;

@Repository
public class UserAuthLogDao extends CustomBaseDao {

    public UserAuthLogDao() {
        this.NAMESPASE = "honc.protoss.core.commons.mapper.UserAuthLogMapper";
    }
}
