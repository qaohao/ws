package com.allyes.weimax.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allyes.weimax.SpringTestCase;
import com.allyes.weimax.support.mybatis.GenericMyBatisDao;

public class WxSubuserTest extends SpringTestCase {
	@Autowired
	private GenericMyBatisDao mybatisDao;
	
	@Test
	public void test() {
		WxSubuser wxSubuser = mybatisDao.get("wxSubuserSqlMapper.findOne");
		System.out.println(wxSubuser);
	}

}
