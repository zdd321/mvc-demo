package com.zdd.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations={"classpath:spring-redis.xml"})
public class RedisTemplateUtil {

	
	private RedisTemplate redisTemplate;  
	  
    public RedisTemplateUtil(RedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
  
  
    
    public void set(String key, Object value) {  
        ValueOperations valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value);  
  
        
        //BoundValueOperations�����Ա����ֵ��һЩϸ΢�Ĳ���  
//        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);  
    }  
  
    public Object get(String key) {  
        return redisTemplate.opsForValue().get(key);  
    }  
  
    public void setList(String key, List<?> value) {  
        ListOperations listOperations = redisTemplate.opsForList();  
        listOperations.leftPush(key, value);  
    }  
  
    public Object getList(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    public void setSet(String key, Set<?> value) {  
        SetOperations setOperations = redisTemplate.opsForSet();  
        setOperations.add(key, value);  
    }  
  
    public Object getSet(String key) {  
        return redisTemplate.opsForSet().members(key);  
    }  
  
  
    public void setHash(String key, Map<String, ?> value) {  
        HashOperations hashOperations = redisTemplate.opsForHash();  
        hashOperations.putAll(key, value);  
    }  
  
    public Object getHash(String key) {  
        return redisTemplate.opsForHash().entries(key);  
    }  
  
  
    public void delete(String key) {  
        redisTemplate.delete(key);  
    }  
}
