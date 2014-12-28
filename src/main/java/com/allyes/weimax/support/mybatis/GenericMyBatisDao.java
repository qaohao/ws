package com.allyes.weimax.support.mybatis;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 简单MyBatis框架Dao模板类。
 * 
 * @author Qaohao
 */
public class GenericMyBatisDao extends SqlSessionDaoSupport {

	public void save(String key, Object object) {
		getSqlSession().insert(key, object);
	}

	public void delete(String key, Serializable id) {
		getSqlSession().delete(key, id);
	}

	public void delete(String key, Object object) {
		getSqlSession().delete(key, object);
	}

	public <T> T get(String key) {
		return (T) getSqlSession().selectOne(key);
	}
	
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().selectOne(key, params);
	}

	public <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}

	public <T> List<T> getList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}
}