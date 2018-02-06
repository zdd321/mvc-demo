package com.zdd.web.entity;

import java.io.Serializable;
/**
 * 照片详情实体，用来记录每张照片的信息
 * @author zhaodd
 *
 */
public class ImageDetail implements Serializable{
    private Long id;//主键
    private String name;//照片名称
    private String img_url;//存储路径
    private String smallimg_url;//缩略图存储路径，暂未使用
    private String status;//图片状�?�，正常，模糊，不符�?
    private Integer img_num;//照片数量
    private Long order4id;//产品ID
    private Double ckb;//长宽�?
    private String uuid;
    private long lownum;//图片�?
     private long longnum;//图片�?
    private String rectsize;//裁剪详情
    private String resize;//操作状�?�，裁切或留�?
    private String hashkey;//图片生成的哈希�?�，用来判断是否重复
    
    
    public String getHashkey() {
		return hashkey;
	}

	public void setHashkey(String hashkey) {
		this.hashkey = hashkey;
	}

	public String getSmallimg_url() {
		return smallimg_url;
	}

	public void setSmallimg_url(String smallimg_url) {
		this.smallimg_url = smallimg_url;
	}

	public String getRectsize() {
		return rectsize;
	}

	public void setRectsize(String rectsize) {
		this.rectsize = rectsize;
	}

	public String getResize() {
		return resize;
	}

	public void setResize(String resize) {
		this.resize = resize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public long getLownum() {
		return lownum;
	}

	public void setLownum(long lownum) {
		this.lownum = lownum;
	}

	public long getLongnum() {
		return longnum;
	}

	public void setLongnum(long longnum) {
		this.longnum = longnum;
	}

	private ImageZancun image;

    public ImageZancun getImage() {
		return image;
	}

	public void setImage(ImageZancun image) {
		this.image = image;
	}

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getImg_num() {
        return img_num;
    }

    public void setImg_num(Integer img_num) {
        this.img_num = img_num;
    }

    public Long getOrder4id() {
        return order4id;
    }

    public void setOrder4id(Long order4id) {
        this.order4id = order4id;
    }

    public Double getCkb() {
        return ckb;
    }

    public void setCkb(Double ckb) {
        this.ckb = ckb;
    }

	
    
}