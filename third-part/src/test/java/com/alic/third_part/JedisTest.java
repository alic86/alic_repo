package com.alic.third_part;

import org.junit.Before;
import org.junit.Test;

import com.lefu.bean.Student;
import com.lefu.util.CacheUtils;
import com.lefu.util.JsonUtils;

import redis.clients.jedis.Jedis;

public class JedisTest {
	private Jedis jedis;
	
	@Before
	public void setup() {
		//连接redis服务器，192.168.0.100:6379
		jedis = new Jedis("127.0.0.1", 6379);
		//权限认证
//		jedis.auth("admin");  
	}
	
	@Test
	public void test01(){
		Student student = new Student();
		student.setId(1L);
		student.setName("jordon");
		student.setAge(44);
		student.setHeight(198.2);
		
		String jsonString = JsonUtils.toJsonString(student);
		String className = Student.class.getSimpleName();
		
//		Long incr = CacheUtils.incr(className+":cnt");
		Long incr = jedis.incr(className+":cnt");
		
		jedis.hset(className, incr.toString(), jsonString);
	}
	
	@Test
	public void test02(){
		String className = "Student";
		String hget = jedis.hget(className, "1");
		System.out.println(hget);
	}
}
