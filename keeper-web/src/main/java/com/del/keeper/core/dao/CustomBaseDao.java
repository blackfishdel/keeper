package com.del.keeper.core.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据库数据操作
 *
 * @author xie
 */
public abstract class CustomBaseDao extends SqlSessionDaoSupport {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    // mapper.xml配置的namespase
    protected String NAMESPASE;

    public Object insert(Object record) {
        this.getSqlSession().insert(this.NAMESPASE + ".insert", record);
        return record;
    }

    public int insertSelective(Object record) {
        return this.getSqlSession().insert(this.NAMESPASE + ".insertSelective", record);
    }

    public int updateByPrimaryKey(Object record) {
        return this.getSqlSession().update(this.NAMESPASE + ".updateByPrimaryKey", record);
    }

    public int updateByPrimaryKeySelective(Object record) {
        return this.getSqlSession().update(this.NAMESPASE + ".updateByPrimaryKeySelective", record);
    }

    public int deleteByPrimaryKey(Object id) {
        return this.getSqlSession().delete(this.NAMESPASE + ".deleteByPrimaryKey", id);
    }

    public Object selectByPrimaryKey(Object id) {
        return this.getSqlSession().selectOne(this.NAMESPASE + ".selectByPrimaryKey", id);
    }
}
