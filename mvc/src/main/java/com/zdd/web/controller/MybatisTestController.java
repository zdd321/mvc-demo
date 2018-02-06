package com.zdd.web.controller;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zdd.web.dao.ImageDetailMapper;
import com.zdd.web.dao.ImageHistoryMapper;
import com.zdd.web.dao.ImageZancunMapper;
import com.zdd.web.dao.UserZancunMapper;
import com.zdd.web.entity.ImageDetail;
import com.zdd.web.entity.ImageZancun;
import com.zdd.web.entity.UserZancun;
import com.zdd.web.service.MybatisTestService;


@Controller
@RequestMapping("/")
public class MybatisTestController {

	@Resource
	private MybatisTestService service;
	@Resource
	private UserZancunMapper userDao;
	@Resource
	private ImageZancunMapper imzDao;
	@Resource
	private ImageDetailMapper imdDao;
	@Resource
	private ImageHistoryMapper imhDao;
	
	@RequestMapping("test")
	@ResponseBody
	public String test(HttpServletRequest req){
		long id=59691220138017961L;
		return service.getUser(id);
	}
	
	@RequestMapping("batchtest")
	@ResponseBody
	public String batchtest(HttpServletRequest req){
		JSONObject json=new JSONObject();
		long id=201801270912L;
		UserZancun user=userDao.selectByPrimaryKey(id);
		List<ImageZancun> imz=imzDao.selectByOrderid(user.getId());
		long begin=new Date().getTime();
		for(ImageZancun imagezancun:imz){
			List<ImageDetail> imdlist=imdDao.selectByOrder4Id(imagezancun.getId());
			imhDao.batchInsert(imdlist);
		}
		long end=new Date().getTime();
		json.put("json", (end-begin)+"s");
		return json.toString();
	}
	
	@RequestMapping("batchtestsingle")
	@ResponseBody
	public String batchtest1(HttpServletRequest req){
		JSONObject json=new JSONObject();
		long id=201801270912L;
		UserZancun user=userDao.selectByPrimaryKey(id);
		List<ImageZancun> imz=imzDao.selectByOrderid(user.getId());
		long begin=new Date().getTime();
		for(ImageZancun imagezancun:imz){
			List<ImageDetail> imdlist=imdDao.selectByOrder4Id(imagezancun.getId());
			for(ImageDetail imd:imdlist){
				imhDao.insert(imd);
			}
		}
		long end=new Date().getTime();
		json.put("json", (end-begin)+"s");
		return json.toString();
	}
}
