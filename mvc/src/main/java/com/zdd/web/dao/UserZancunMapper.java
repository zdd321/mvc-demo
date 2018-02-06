package com.zdd.web.dao;

import java.util.List;

import com.zdd.web.entity.UserZancun;

public interface UserZancunMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserZancun record);
    
    int insertStatus(UserZancun record);

    UserZancun selectByPrimaryKey(Long id);

    List<UserZancun> selectAll();
    
    UserZancun selectByStatus(Long id);

    int updateByPrimaryKey(UserZancun record);

	void setIsFillIn(UserZancun userZancun);
	void setStatus(UserZancun userZancun);
	int countByOrderid(Long orderid);
	List<Long> findByStatus(Long orderid);
	void setUploadStatus(UserZancun userZancun);

	int resetStyle(long orderid);
	List<UserZancun> selectByCreatetime();
	int updateSubmitById(UserZancun userZancun);
	int updateSubmitMethod(UserZancun userZancun);
	int updateIsFillIn(UserZancun user);

	int updateMembername(UserZancun user);
}