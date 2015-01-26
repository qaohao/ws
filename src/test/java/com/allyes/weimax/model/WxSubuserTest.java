package com.allyes.weimax.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allyes.weimax.SpringTestCase;
import com.allyes.weimax.support.mybatis.GenericMyBatisDao;
import com.allyes.weimax.support.weixin.WxSubuserHelper;
import com.allyes.weimax.utils.MapBuilder;

public class WxSubuserTest extends SpringTestCase {
	@Autowired
	private GenericMyBatisDao mybatisDao;
	
	@Test
	public void test() {
		WxSubuser wxSubuser = mybatisDao.get("wxSubuserSqlMapper.findOne");
		System.out.println(wxSubuser);
	}
	
	@Test
	public void testGetByAccountId() {
		List<WxSubuser> wxSubuserList = mybatisDao.getList(
				"wxSubuserSqlMapper.findByAccountId", 32);
		System.out.println(wxSubuserList.size());
	}
	
	@Test
	public void testBatchAdd() {
		long accountId = 32;
		String openId = "";
		String appId = "wxaaddc714663916e0";
		String appSecret = "afb9dcf6cbd4ac5bc02fa949afae803f";
		
		final WxMpConfigStorage wxConfig = WxSubuserHelper.newWxMpConfig(appId,
				appSecret);
		List<WxMpUser> subuserList = WxSubuserHelper.batchGetSubuserInfo(
					wxConfig, Arrays.asList(
							"opw1FuPOVEaY8OaU7AY1J-YPtmoo",
							"opw1FuAHoNLbo8B3hBeYOv-vA9K4"));
		System.out.println(subuserList);
		mybatisDao.save("wxSubuserSqlMapper.batchAdd",
				MapBuilder.create(HashMap.class)
					.add("accountId", accountId)
					.add("subuserList", subuserList).map());
	}
	
}
