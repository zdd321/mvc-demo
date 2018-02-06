package com.zdd.web.dao;

import java.util.List;

import com.zdd.web.entity.ImageZancun;



public interface ImageZancunMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByOrderId(Long orderid);
    long insert(ImageZancun record);

    ImageZancun selectByPrimaryKey(Long orderId);

    List<ImageZancun> selectAll();

    int updateByPrimaryKey(ImageZancun record);
    ImageZancun findByOrderId(Long orderid);
    
    List<ImageZancun> selectByOrderid(Long orderid); //zn
    
    List<ImageZancun> selectId(Long orderid);
    
    int selectCountByOrderId(Long orderid);
	int updateFilePath(ImageZancun record);
}