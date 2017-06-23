package com.del.keeper.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.del.keeper.commons.entity.Account;
import com.del.keeper.core.dao.CustomBaseDao;

/**
 * Created by xie
 */
@Repository
public class AccountDao extends CustomBaseDao {

    public AccountDao() {
        this.NAMESPASE = "com.del.keeper.commons.mepper.GoldAccountMapper";
    }

    public List<Account> selectListByPrimaryKeySelective(Object record) {
        return this.getSqlSession().selectList(this.NAMESPASE + ".selectListByPrimaryKeySelective", record);
    }
}
