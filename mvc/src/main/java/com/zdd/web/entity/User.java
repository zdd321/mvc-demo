package com.zdd.web.entity;

public class User {

    private Long id;
    private String memberid;
    private String membername;
    private String isFillIn;
    private String status;
    private int uploadStatus;//0:�����ϴ�; 1:�ֻ��ϴ���; 2:�ֻ��ϴ����ӹ���,��Ҫ����ɨ��;
    private int submitMethod;//�ύ��ʽ��0��PC��1���ֻ�
    

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
