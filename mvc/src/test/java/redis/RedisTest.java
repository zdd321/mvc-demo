package redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zdd.util.RedisTemplateUtil;
import com.zdd.web.dao.UserZancunMapper;
import com.zdd.web.entity.UserZancun;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-redis.xml","classpath:spring-mybatis.xml"})
public class RedisTest {

	static Logger log=Logger.getLogger(RedisTemplateUtil.class);
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private UserZancunMapper userDao;
	
	@Test  
    public void testSpringRedis() {  
		log.info("11111");
        log.debug("123");
        log.error("error");
        //stringRedisTemplate�Ĳ���  
        // String��д  
        redisTemplate.delete("myStr");  
        redisTemplate.opsForValue().set("myStr", "skyLine");  
        System.out.println(redisTemplate.opsForValue().get("myStr"));  
        System.out.println("---------------"); 
        redisTemplate.delete("userid:"+201712182410L);
        redisTemplate.opsForValue().set("userid:"+201712182410L, userDao.selectByPrimaryKey(201712182410L).toString());
//        System.out.println(redisTemplate.opsForValue().get("userid:"+201712182410L));
        UserZancun userZancun=JSON.toJavaObject(
        		JSON.parseObject(redisTemplate.opsForValue().get("userid:"+201712182410L).toString()), 
        		UserZancun.class);
        System.out.println(userZancun);
        
        // List��д  
        redisTemplate.delete("myList");  
        redisTemplate.opsForList().rightPush("myList", "T");  
        redisTemplate.opsForList().rightPush("myList", "L");  
        redisTemplate.opsForList().leftPush("myList", "A");  
        List<String> listCache = redisTemplate.opsForList().range(  
                "myList", 0, -1);  
        for (String s : listCache) {  
            System.out.println(s);  
        }  
        System.out.println("---------------");  
  
        // Set��д  
        redisTemplate.delete("mySet");  
        redisTemplate.opsForSet().add("mySet", "A");  
        redisTemplate.opsForSet().add("mySet", "B");  
        redisTemplate.opsForSet().add("mySet", "C");  
        Set<String> setCache = redisTemplate.opsForSet().members(  
                "mySet");  
        for (String s : setCache) {  
            System.out.println(s);  
        }  
        System.out.println("---------------");  
  
        // Hash��д  
        redisTemplate.delete("myHash");  
        redisTemplate.opsForHash().put("myHash", "BJ", "����");  
        redisTemplate.opsForHash().put("myHash", "SH", "�Ϻ�");  
        redisTemplate.opsForHash().put("myHash", "HN", "����");  
        Map<String, String> hashCache = redisTemplate.opsForHash()  
                .entries("myHash");  
        for (Map.Entry entry : hashCache.entrySet()) {  
            System.out.println(entry.getKey() + " - " + entry.getValue());  
        }  
        System.out.println("---------------");  
    }  
}
