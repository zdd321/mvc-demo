package com.zdd.web.entity;

import java.util.List;
/**
 * 产品实体，用来记录每个订单下产品的信�?
 * @author Administrator
 *
 */
public class ImageZancun {
    private Long id;
	private String spsl;//照片数量
	private String yspsl;
	private Long orderid;//订单�?
	private String attrs;//规格
	private Double sizescale;//标准长宽�?
	private Double errorrangelow;//长宽比误差下�?
	private Double errorrangehigh;//长宽比误差上�?
	private Integer pixelup;//标准像素
	private Integer pixellow;//像素下线
	private String spmc;//产品名称
	private String filepath;//推�?�目�?
	
    

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	private UserZancun user;
    
    public UserZancun getUser() {
		return user;
	}

	public void setUser(UserZancun user) {
		this.user = user;
	}
	
	private List<ImageDetail> allDetail;

    public List<ImageDetail> getAllDetail() {
		return allDetail;
	}

	public void setAllDetail(List<ImageDetail> allDetail) {
		this.allDetail = allDetail;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpsl() {
        return spsl;
    }

    public void setSpsl(String spsl) {
        this.spsl = spsl;
    }

    public String getYspsl() {
        return yspsl;
    }

    public void setYspsl(String yspsl) {
        this.yspsl = yspsl;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public Double getSizescale() {
        return sizescale;
    }

    public void setSizescale(Double sizescale) {
        this.sizescale = sizescale;
    }

    public Double getErrorrangelow() {
        return errorrangelow;
    }

    public void setErrorrangelow(Double errorrangelow) {
        this.errorrangelow = errorrangelow;
    }

    public Double getErrorrangehigh() {
        return errorrangehigh;
    }

    public void setErrorrangehigh(Double errorrangehigh) {
        this.errorrangehigh = errorrangehigh;
    }

    public Integer getPixelup() {
        return pixelup;
    }

    public void setPixelup(Integer pixelup) {
        this.pixelup = pixelup;
    }

    public Integer getPixellow() {
        return pixellow;
    }

    public void setPixellow(Integer pixellow) {
        this.pixellow = pixellow;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }
}