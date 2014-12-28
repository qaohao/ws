package com.allyes.weimax.service;

/**
 * 微信公众号同步服务类。
 * 
 * @author qaohao
 */
public interface IWcsSyncService {
	/**
	 * 同步分组信息。
	 */
	void syncGroup();
	
	/**
	 * 同步公众号粉丝信息。
	 */
	void syncSubusers();
	
	/**
	 * 同步指定粉丝信息。
	 */
	void syncSubuser(String appId, String appSecret, String openId);
}
