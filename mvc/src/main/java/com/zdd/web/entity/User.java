package com.zdd.web.entity;

public class User {

    private Long id;
    private String memberid;
    private String membername;
    private String isFillIn;
    private String status;
    private int uploadStatus;//0:可以上传; 1:手机上传中; 2:手机上传链接过期,需要重新扫码;
    private int submitMethod;//提交方式；0：PC；1：手机
    

   public int getSubmitMethod() {
		return submitMethod;
	}

	public void setSubmitMethod(int submitMethod) {
		this.submitMethod = submitMethod;
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
    
}
