package com.zdd.web.entity;

import java.sql.Timestamp;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 订单实体，记录订单相关信�?
 * @author zhaodd
 *
 */
public class UserZancun {
    private Long id;
    private String memberid;
    private String membername;
    private String isFillIn;
    private String status;
    private int uploadStatus;//0:可以上传; 1:手机上传�?; 2:手机上传链接过期,�?要重新扫�?;
    private int submitMethod;//提交方式�?0：PC�?1：手�?
    private int sourcetype;// 订单来源�?0：淘宝；1：京�?
    private String browser;//浏览器版�?
    private String mobiletype;//手机型号
    private String os;//操作系统
    private Timestamp createtime;//上传时间
    private List<ImageZancun> allImages;
    
    
   public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getMobiletype() {
		return mobiletype;
	}

	public void setMobiletype(String mobiletype) {
		this.mobiletype = mobiletype;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

public int getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(int sourcetype) {
		this.sourcetype = sourcetype;
	}

	public int getSubmitMethod() {
		return submitMethod;
	}

	public void setSubmitMethod(int submitMethod) {
		this.submitMethod = submitMethod;
	}

	public List<ImageZancun> getAllImages() {
		return allImages;
	}

	public void setAllImages(List<ImageZancun> allImages) {
		this.allImages = allImages;
	}
	
    public String getIsFillIn() {
		return isFillIn;
	}

	public void setIsFillIn(String isFillIn) {
		this.isFillIn = isFillIn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

	public int getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	@Override
	public String toString() {
		return JSON.toJSON(this).toString();
	}
    
}