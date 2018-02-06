package com.zdd.web.service;



import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zdd.web.dao.UserZancunMapper;


@Service
public class MybatisTestService {
	
	private static Logger log=Logger.getLogger(MybatisTestService.class);

	@Resource
	private UserZancunMapper userDao;
	
	public String getUser(long id){
		Object User = userDao.selectAll();
		System.out.println(User);
		return "";
	}
}
