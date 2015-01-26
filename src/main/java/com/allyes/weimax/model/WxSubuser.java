package com.allyes.weimax.model;

import java.util.Date;

/**
 * 微信公众号粉丝表。
 * 
 * @author qaohao
 */
public class WxSubuser {
	private long id;
	private long wxServiceAccountId;
	private String unionOpenId;
	private String openId;
	private String hashOpenId;
	private String unionId;
	private int isSubscribed;
	private String nickname;
	private int sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headImgUrl;
	private long subscribeTime;
	private Date lastUpadte;
	private String channelId;
	private String RealLocation;
	private String groupId;
	private String address;
	private String note;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWxServiceAccountId() {
		return wxServiceAccountId;
	}
	public void setWxServiceAccountId(long wxServiceAccountId) {
		this.wxServiceAccountId = wxServiceAccountId;
	}
	public String getUnionOpenId() {
		return unionOpenId;
	}
	public void setUnionOpenId(String unionOpenId) {
		this.unionOpenId = unionOpenId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getHashOpenId() {
		return hashOpenId;
	}
	public void setHashOpenId(String hashOpenId) {
		this.hashOpenId = hashOpenId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public int getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(int isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public long getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public Date getLastUpadte() {
		return lastUpadte;
	}
	public void setLastUpadte(Date lastUpadte) {
		this.lastUpadte = lastUpadte;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getRealLocation() {
		return RealLocation;
	}
	public void setRealLocation(String realLocation) {
		RealLocation = realLocation;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "WxSubuser [id=" + id + ", wxServiceAccountId="
				+ wxServiceAccountId + ", unionOpenId=" + unionOpenId
				+ ", openId=" + openId + ", hashOpenId=" + hashOpenId
				+ ", unionId=" + unionId + ", isSubscribed=" + isSubscribed
				+ ", nickname=" + nickname + ", sex=" + sex + ", language="
				+ language + ", city=" + city + ", province=" + province
				+ ", country=" + country + ", headImgUrl=" + headImgUrl
				+ ", subscribeTime=" + subscribeTime + ", lastUpadte="
				+ lastUpadte + ", channelId=" + channelId + ", RealLocation="
				+ RealLocation + ", groupId=" + groupId + ", address="
				+ address + ", note=" + note + "]";
	}
}
