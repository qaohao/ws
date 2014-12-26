package com.allyes.weimax.service;

import java.util.LinkedList;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

import org.springframework.stereotype.Service;

import com.allyes.weimax.Constants;

@Service
public class WcsSubuserService {
	public static void main(String[] args) {
		WcsSubuserService service = new WcsSubuserService();
		WxMpInMemoryConfigStorage wxConfig = service.newWxMpConfig("wx1fe92ab266bc2732", "8b8e3fa3e4e238114b65389424dc9dd0");
		List<String> userList = service.getSubuserList(wxConfig);
		System.out.println(userList);
		
		List<WxMpUser> userInfoList = service.batchGetSubuserInfo(wxConfig, userList);
		System.out.println(userInfoList.size());
		System.out.println(userInfoList);
	}
	
	/*
	 * 创建 WxMpInMemoryConfigStorage对象。
	 */
	WxMpInMemoryConfigStorage newWxMpConfig(String appId, String appSecret) {
		WxMpInMemoryConfigStorage wxConfig = new WxMpInMemoryConfigStorage();
		wxConfig.setAppId(appId);
		wxConfig.setSecret(appSecret);
		return wxConfig;
	}
	
	/*
	 * 获取关注用户列表。
	 */
	List<String> getSubuserList(WxMpInMemoryConfigStorage wxConfig) {
		List<String> openIdList = new LinkedList<String>();
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		try {
			WxMpUserList userList = wxMpService.userList(Constants.EMPTY);
			
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
	List<WxMpUser> batchGetSubuserInfo(WxMpInMemoryConfigStorage wxConfig,
			List<String> openIdList) {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
		
		List<WxMpUser> userList = new LinkedList<WxMpUser>();
		for (String openId : openIdList) {
			try {
				userList.add(wxMpService.userInfo(openId, Constants.LANG));
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}
}