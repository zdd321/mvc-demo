package com.zdd.web.dao;

import java.util.List;

import com.zdd.web.entity.ImageDetail;

public interface ImageHistoryMapper {

	int batchInsert(List<ImageDetail> image);
	int insert(ImageDetail image);
}
