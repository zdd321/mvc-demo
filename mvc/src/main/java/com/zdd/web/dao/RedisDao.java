package com.zdd.web.dao;

import javax.annotation.Resource;
import javax.jms.ResourceAllocationRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zdd.web.entity.UserZancun;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="RedisConnectionFactory")
	private  RedisConnectionFactory jedisPool;
	
	private RuntimeSchema<UserZancun> schema = RuntimeSchema.createFrom(UserZancun.class);
	
	public UserZancun getUserZancun(Long userid){
		//redis操作逻辑
		try {
			RedisConnection jedis = jedisPool.getConnection();
			try {
				String key="userzancun:"+userid;
				//序列化
				//get->byte[]->反序列化->Object
				//采用自定义序列化
				//protostuff:pojo
				byte[] bytes=jedis.get(key.getBytes());
				if(bytes!=null){
					UserZancun userZancun=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, userZancun, schema);
					return userZancun;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public String putUserZancun(UserZancun user){
		//set Object->序列化-》byte[]->redis
		try {
			RedisConnection jedis=jedisPool.getConnection();
			try {
				String key="userzancun:"+user.getId();
				byte[] bytes =ProtostuffIOUtil.toByteArray(user, schema, 
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				int timeout=60*60;
				jedis.setEx(key.getBytes(), timeout, bytes);
				return "ok";
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String deleteUserZancun(long id){
		try {
			RedisConnection jedis=jedisPool.getConnection();
			long l=jedis.del(("userzancun:"+id).getBytes());
			return String.valueOf(l);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
