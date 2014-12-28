package com.allyes.weimax.support.weixin;

import java.util.List;

import junit.framework.TestCase;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WxSubuserHelperTest extends TestCase {
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
}
