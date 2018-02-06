package com.zdd.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zdd.web.entity.ImageDetail;



public interface ImageDetailMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByOrderId(Long orderId);
    long insert(ImageDetail record);
    ImageDetail selectByPrimaryKey(Long id);
    List<ImageDetail> selectAll();
    int updateByPrimaryKey(ImageDetail record);
    int updateNumById(ImageDetail record);
    List<Map<String, Object>> selectAttrsAndImgNumByOrderId(Long orderId);
    List<Map<String,Object>> selectfilepathByorderId(Long orderId);
    List<ImageDetail> selectByOrder4Id(Long order4id);
    List<ImageDetail> selectByOrder4IdCut(Long order4id);
    List<ImageDetail> selectByOrder4IdAndStatus(Long order4id);
    ImageDetail findByUuid(String uuid);
    int deleteByUuid(String uuid);
    List<ImageDetail> selectLimit(@Param("order4id") long order4id,@Param("eh") double eh,@Param("el") double el,@Param("pagesize") int pagesize, @Param("offset") int offset);
    List<ImageDetail> selectByLimit(@Param("order4id") long order4id,@Param("eh") double eh,@Param("el") double el);
    int selectCutCount(@Param("order4id") long order4id,@Param("eh") double eh,@Param("el") double el);
    int selectAllCount(Long order4id);
    List<Map<String,Object>> selecByGroup(Long orderId);
    List<ImageDetail> selecAll(@Param("order4id") long order4id,@Param("imgnum") int imgnum);
    void updateFillin(@Param("rectsize") String rectsize,@Param("resize") String resize,@Param("id") long id);
    int selectCountByOrderId(Long orderid);
    int countBystatus(Long id);
	int updateCutInfo(ImageDetail imd);
	List<ImageDetail> selectByresize(long orderid);
	int updateResize(ImageDetail img);
	ImageDetail selectImd(ImageDetail img);
	List<ImageDetail> selectUrl(Long orderid);
}