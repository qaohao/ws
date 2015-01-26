package com.allyes.weimax.support.weixin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpGroup;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

import com.allyes.weimax.Constants;
import com.allyes.weimax.service.impl.WcsSyncService;

/**
 * 微信公众平台用户接口帮助类。
 * 
 * @author qaohao
 */
public class WxSubuserHelper {
	private static final Logger LOG = Logger.getLogger(WxSubuserHelper.class);
	
	/*
	 * 创建 WxMpInMemoryConfigStorage对象。
	 */
	public static WxMpInMemoryConfigStorage newWxMpConfig(String appId, String appSecret) {
		WxMpInMemoryConfigStorage wxConfig = new WxMpInMemoryConfigStorage();
		wxConfig.setAppId(appId);
		wxConfig.setSecret(appSecret);
		return wxConfig;
	}

	/*
	 * 获取微信分组信息
	 */
	public static List<WxMpGroup> getGroupList(WxMpConfigStorage wxConfig) {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		List<WxMpGroup> groupList = null;
		try {
			groupList = wxMpService.groupGet();
		} catch (WxErrorException e) {
			e.printStackTrace();
		} 
		return groupList;
	}
	
	/*
	 * 获取微信公众号粉丝所在分组 
	 */
	public static Long getGroupId(WxMpConfigStorage wxConfig, String openId) {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		Long groupId = null;
		try {
			groupId = wxMpService.userGetGroup(openId);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return groupId;
	}
	
	/*
	 * 获取关注用户列表。
	 */
	public static List<String> getSubuserList(WxMpConfigStorage wxConfig) {
		List<String> openIdList = new LinkedList<String>();
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		try {
			WxMpUserList userList = wxMpService.userList(Constants.EMPTY);
			LOG.info(userList.getTotal());
			
			int total = userList.getTotal();
			int count = userList.getCount();
			while (count < total) {
				openIdList.addAll(userList.getOpenIds());
				userList = wxMpService.userList(userList.getNextOpenId());
				count += userList.getCount();
			}
			openIdList.addAll(userList.getOpenIds());

		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return openIdList;
	}

	/*
	 * 批量获取关注用户信息。
	 */
	public static List<WxMpUser> batchGetSubuserInfo(WxMpConfigStorage wxConfig,
			List<String> openIdList) {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		
		System.out.println(openIdList.size()+","+openIdList);
		List<WxMpUser> userList = new LinkedList<WxMpUser>();
		for (String openId : openIdList) {
			try {
				WxMpUser subuser = wxMpService.userInfo(openId, Constants.LANG);
				userList.add(subuser);
				LOG.info(subuser);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}
}
