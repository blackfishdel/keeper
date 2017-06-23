package com.del.keeper.commons.dao;

import org.springframework.stereotype.Repository;

import com.del.keeper.core.dao.CustomBaseDao;

/**
 * Created by xie
 */
@Repository
public class AccountLogDao extends CustomBaseDao {
    public AccountLogDao() {
        this.NAMESPASE = "com.del.keeper.commons.mepper.GoldAccountLogMapper";
    }
}
