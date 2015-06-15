package com.gooker.dfg.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.aoeng.degu.constant.Constants;
import com.aoeng.degu.domain.ImageInfo;
import com.aoeng.degu.utils.common.StringUtils;

public class ImageGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7155071219748681643L;
	/**
	 * 编号
	 */
	private long groupId;
	/**
	 * 拍摄最晚日期
	 */
	private Date maxDate;
	/**
	 * 最早拍摄时间
	 */
	private Date minDate;
	/**
	 * 拍摄地理位置
	 */
	private String loaction;
	/**
	 * 拍照维度
	 */
	private Double latitude;
	/**
	 * 拍照经度
	 */
	private Double longitude;
	/**
	 * 分组 首页照片 地址
	 */
	private String frontImgPath;
	/**
	 * 分组 首页照片 缩略图地址
	 */
	private String frontCacheImgPath;
	/**
	 * 
	 */
	// private String contactImgs;
	private int albumSize;

	private List<ImageInfo> infos = new ArrayList<ImageInfo>();

	public void add(ImageInfo info) {
		if (null == info) {
			return;
		}

		if (null == this.minDate || minDate.getTime() > info.getTakenDate().getTime()) {
			this.minDate = info.getTakenDate();
		}
		if (null == this.maxDate || maxDate.getTime() < info.getTakenDate().getTime()) {
			this.maxDate = info.getTakenDate();
		}
		if (null == this.latitude || 0.0 == this.latitude || null == this.longitude || 0.0 == this.longitude) {
			this.latitude = info.getLatitude();
			this.longitude = info.getLongitude();
		}
		if (StringUtils.isEmpty(this.frontImgPath)) {
			this.frontImgPath = info.getPicPath();
		}
		if (0 == this.groupId || this.groupId < info.getTakenDate().getTime()) {
			this.groupId = info.getTakenDate().getTime();
		}

		infos.add(info);
		this.albumSize = this.infos.size();
		StringBuffer sb = new StringBuffer();
		this.frontImgPath = info.getPicPath();
		// for (ImageInfo ifo : this.infos) {
		// sb.append(JSON.toJSONString(ifo)).append(";");
		//
		// }
		// String str = sb.toString().substring(0,
		// sb.toString().lastIndexOf(";"));

		// this.contactImgs = str;

	}

	public long getGroupId() {
		return groupId;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public Date getMinDate() {
		return minDate;
	}

	public String getLoaction() {
		return loaction;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public List<ImageInfo> getInfos() {
		return infos;
	}

	public String getFrontImgPath() {
		return frontImgPath;
	}

	public void setLoaction(String loaction) {
		this.loaction = loaction;
	}

	public String getFrontCacheImgPath() {
		return frontCacheImgPath;
	}

	public void setFrontCacheImgPath(String frontCacheImgPath) {
		this.frontCacheImgPath = frontCacheImgPath;
	}

	/**
	 * Image 属于 该 组 属于 就添加，不属于 就返回 false
	 * 
	 * @param info
	 * @return
	 */
	public boolean isBelong(ImageInfo info) {
		// TODO Auto-generated method stub
		if (info.getTakenDate().getTime() < this.maxDate.getTime() + Constants.GroupMaxMillisecond && info.getTakenDate().getTime() > this.minDate.getTime() - Constants.GroupMaxMillisecond) {
			// LogUtils.i("isBelong--------------true");
			return true;
		}
		// LogUtils.i("isBelong--------------false");
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		this.groupId = 0;
		this.infos.clear();
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.maxDate = null;
		this.minDate = null;
		this.loaction = "";
	}

	/**
	 * 判断 时间 beginTime 是不是属于 该 group
	 * 
	 * @param begTime
	 */
	public boolean isBelongGroup(long begTime) {
		// TODO Auto-generated method stub
		if (Math.abs(this.minDate.getTime() - begTime) - Constants.GroupMaxMillisecond > 0 || Math.abs(this.maxDate.getTime() - begTime) - Constants.GroupMaxMillisecond > 0) {
			return false;
		}
		return true;
	}

	public void setAlbumSize(int albumSize) {
		this.albumSize = albumSize;
	}

	public int getAlbumSize() {
		return this.albumSize;
	}

	public boolean isSameGroup(com.aoeng.degu.domain.ImageGroup imageGroup) {
		// TODO Auto-generated method stub
		if (isBelongGroup(imageGroup.getMinDate().getTime()) || isBelongGroup(imageGroup.getMaxDate().getTime())) {
			return true;
		}
		return false;
	}

	public void setInfos(List<ImageInfo> list) {
		this.infos = list;
	}

	@Override
	public String toString() {
		return "ImageGroup [groupId=" + groupId + ", maxDate=" + maxDate + ", minDate=" + minDate + ", loaction=" + loaction + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", frontImgPath=" + frontImgPath + ", frontCacheImgPath=" + frontCacheImgPath + ", albumSize=" + albumSize + ", infos=" + infos + "]";
	}

}
