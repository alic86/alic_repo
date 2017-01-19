package com.alic.third_part;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.alic.bean.Student;
import com.alic.common.DBUtil;

public class DBTest {
	@Test
	public void test01(){
		DBUtil dbUtil = new DBUtil();
		StringBuffer sql = new StringBuffer();
		sql.append(" select stu.id,stu.name,stu.age,stu.height,stu.subject_id subjectId,sub.name subjectName ");
		sql.append(" from lefu.student stu 												");
		sql.append(" left join lefu.subject sub 										");
		sql.append(" on stu.subject_id=sub.id 											");
		sql.append(" where stu.id=? 													");
		
		List<Student> beanList = dbUtil.getBeanList(sql, Student.class,1);
		
		for(Student stu:beanList){
			System.out.print("id:"+stu.getId());
			System.out.print(",name:"+stu.getName());
			System.out.print(",age:"+stu.getAge());
			System.out.print(",height:"+stu.getHeight());
			System.out.print(",subjectId:"+stu.getSubjectId());
			System.out.print(",subjectName:"+stu.getSubjectName());
			System.out.println();
		}
	}
	
	@Test
	public void test02(){
		DBUtil dbUtil = new DBUtil();
		StringBuffer sql = new StringBuffer();
		sql.append(" select stu.id,stu.name,stu.age,stu.height,stu.subject_id subjectId,sub.name subjectName ");
		sql.append(" from lefu.student stu 												");
		sql.append(" left join lefu.subject sub 										");
		sql.append(" on stu.subject_id=sub.id 											");
		sql.append(" where stu.id=? 													");
		
		List<Map<String, Object>> mapList = dbUtil.getBeanListByMap(sql,4);
		
		for(Map<String, Object> map:mapList){
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> next = iterator.next();
				String key = next.getKey();
				Object value = next.getValue();
				System.out.print(" "+key+":"+value);
			}
			System.out.println();
			
		}
	}
	
	@Test
	public void test03(){
		DBUtil dbUtil = new DBUtil();
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into lefu.student(name,age,height,subject_id) values(?,?,?,?) ");
		
		Object[] args = new Object[]{"kitty","15","155",2};
		
		Integer merge = dbUtil.merge(sql,args);
		System.out.println(merge);
	}
	
	@Test
	public void test04(){
		DBUtil dbUtil = new DBUtil();
		StringBuffer sql = new StringBuffer();
		sql.append(" update lefu.student set name=?,age=?,height=?,subject_id=? where id=? ");
		
		Integer merge = dbUtil.merge(sql,"update_user",18,192,2,5);
		System.out.println(merge);
	}
	
	@Test
	public void test05(){
		DBUtil dbUtil = new DBUtil();
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from lefu.student where id=? ");
		
		Integer merge = dbUtil.merge(sql,5);
		System.out.println(merge);
	}
}
