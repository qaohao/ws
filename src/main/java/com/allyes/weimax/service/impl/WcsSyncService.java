package com.allyes.weimax.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpGroup;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allyes.weimax.model.WxSubuser;
import com.allyes.weimax.service.IWcsSyncService;
import com.allyes.weimax.support.mybatis.GenericMyBatisDao;
import com.allyes.weimax.support.weixin.WxSubuserHelper;
import com.allyes.weimax.utils.MapBuilder;

@Service
public class WcsSyncService implements IWcsSyncService {
	private static final Logger LOG = Logger.getLogger(WcsSyncService.class);
	
	@Autowired
	private GenericMyBatisDao myBatisDao;
	
	@Override
	public void syncSubusers() {
		// TODO
		// 加多宝公众号
		long accountId = 32;
		String token = "rJp3o8Ddt3mkbmOBdkZx";
		String appId = "wxaaddc714663916e0";
		String appSecret = "afb9dcf6cbd4ac5bc02fa949afae803f";

		final WxMpConfigStorage wxConfig = WxSubuserHelper.newWxMpConfig(appId,
				appSecret);
		
		// 从微信公众平台获取公众号分组信息。
		List<WxMpGroup> wxGroupList = WxSubuserHelper.getGroupList(wxConfig);
		LOG.info(wxGroupList);
		
		// 保存公众号分组信息。
		myBatisDao.save("wxGroupSqlMapper.batchAdd",
				MapBuilder.create(HashMap.class)
					.add("token", token)
					.add("groupList", wxGroupList)
					.map());
		System.out.println("分组信息已经入库");
		
		// 获取所有关注用户openId。
		final List<String> openIdList = WxSubuserHelper.getSubuserList(wxConfig);
		LOG.info("关注用户数：" + openIdList.size());
		LOG.info("关注用户openId：" + openIdList);
		
		// 删除DB中已经包含的关注用户。
		List<WxSubuser> wxSubuserList = myBatisDao.getList(
				"wxSubuserSqlMapper.findByAccountId", accountId);
		LOG.info("已经入库关注用户数：" + wxSubuserList.size());
		LOG.info("已经入库关注用户信息：" + wxSubuserList);
		for (int i = 0; i < openIdList.size();) {
			boolean found = false;
			for (int j = 0; j < wxSubuserList.size(); j++) {
				if (openIdList.get(i).equals(wxSubuserList.get(j).getOpenId())) {
					openIdList.remove(i);
					wxSubuserList.remove(j);
					found = true;
					break;
				}
			}
			if (!found) {
				i++;
			}
		}
		
		// 获取关注用户详细信息。
		final Vector<WxMpUser> wxSubuserVector = new Vector<WxMpUser>();
		final int total = openIdList.size();
		final int step = total/10 + 1;
		final AtomicInteger cursor = new AtomicInteger(0);
		Executor executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					int startIndex = cursor.getAndAdd(step);
					int toIndex = startIndex + step;
					if (toIndex > total) {
						toIndex = total;
					}
					System.out.println("startIndex" + startIndex + ", toIndex:"
							+ toIndex);
					wxSubuserVector.addAll(WxSubuserHelper.batchGetSubuserInfo(
							wxConfig, openIdList.subList(startIndex, toIndex)));
				}
			});
		}
		
		// 等待同步数据完毕。
		int count = 0;
		while ((count = wxSubuserVector.size()) < total) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("数据同步完毕！");
		
		// 将关注用户详细信息同步dao数据库。
		for (int start = 0; start < total; start += 1000) {
			System.out.println("入库数据start="+start);
			myBatisDao.save("wxSubuserSqlMapper.batchAdd",
					MapBuilder.create(HashMap.class)
						.add("accountId", accountId)
						.add("subuserList", 
								wxSubuserVector.subList(start,
										start + 1000 < total ? start + 1000
												: total)).map());
		}
	}

	@Override
	public void syncSubuser(String appId, String appSecret, String openId) {
	}

	@Override
	public void syncGroup() {
		
	}

}
