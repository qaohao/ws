package com.allyes.weimax.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allyes.weimax.SpringTestCase;
import com.allyes.weimax.support.mybatis.GenericMyBatisDao;

public class SubuserTest extends SpringTestCase {
	@Autowired
	private GenericMyBatisDao mybatisDao;
	
	@Test
	public void test() {
		Subuser subuser = mybatisDao.get("subuserSqlMapper.findOne");
		System.out.println(subuser);
	}

}
