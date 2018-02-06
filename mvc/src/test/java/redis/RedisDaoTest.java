package redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zdd.web.dao.RedisDao;
import com.zdd.web.dao.UserZancunMapper;
import com.zdd.web.entity.UserZancun;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-redis.xml","classpath:spring-mybatis.xml" })
public class RedisDaoTest {

	private long id = 201712182410L;
	@Resource
	private RedisDao redisDao;
	@Resource
	private UserZancunMapper userDao;

	@Test
	public void testRedisDao() throws Exception {
		// get and put
//		String string = redisDao.deleteUserZancun(id);
		UserZancun user = redisDao.getUserZancun(id);
		System.out.println(user);
		if (user == null) {
			user = userDao.selectByPrimaryKey(id);
			if (user != null) {
				String result = redisDao.putUserZancun(user);
				System.out.println(result);
				user=null;
				user = redisDao.getUserZancun(id);
				System.out.println(user.toString());
				System.out.println(user.getId());
			}
		}
	}
}
