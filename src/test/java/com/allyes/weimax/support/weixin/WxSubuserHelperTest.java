package com.allyes.weimax.support.weixin;

import java.util.List;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpGroup;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.junit.Test;

import com.allyes.weimax.SpringTestCase;

public class WxSubuserHelperTest extends SpringTestCase {
	@Test
	public void testAll() {
		WxMpConfigStorage wxConfig = WxSubuserHelper.newWxMpConfig(
				"wx1fe92ab266bc2732", "8b8e3fa3e4e238114b65389424dc9dd0");
		List<String> userList = WxSubuserHelper.getSubuserList(wxConfig);
		System.out.println(userList);

		List<WxMpUser> userInfoList = WxSubuserHelper.batchGetSubuserInfo(
				wxConfig, userList);
		System.out.println(userInfoList.size());
		System.out.println(userInfoList);

		System.out.println(WxSubuserHelper.getGroupList(wxConfig));

		System.out.println(WxSubuserHelper.getGroupId(wxConfig,
				"odmtst24bndH4VHG4xmYa5yEpWTw"));
	}
	
	@Test
	public void testGetUserList() {
		String appId = "wxaaddc714663916e0";
		String appSecret = "afb9dcf6cbd4ac5bc02fa949afae803f";

		final WxMpConfigStorage wxConfig = WxSubuserHelper.newWxMpConfig(appId,
				appSecret);
		
		// 从微信公众平台获取公众号分组信息。
		WxSubuserHelper.getSubuserList(wxConfig);
	}
	
	@Test
	public void testGetGroup() {
		String appId = "wxaaddc714663916e0";
		String appSecret = "afb9dcf6cbd4ac5bc02fa949afae803f";

		final WxMpConfigStorage wxConfig = WxSubuserHelper.newWxMpConfig(appId,
				appSecret);
		
		List<WxMpGroup> groupList = WxSubuserHelper.getGroupList(wxConfig);
		for (WxMpGroup wxMpGroup : groupList) {
			System.out.println(wxMpGroup.getName()+","+wxMpGroup.getCount());
		}
	}
}
