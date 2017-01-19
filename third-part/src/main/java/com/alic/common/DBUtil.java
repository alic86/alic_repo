package com.alic.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class DBUtil {
	private final String url = "jdbc:mysql://localhost:3306/lefu";
	private final String user = "root";
	private final String password = "Alicsxz86*";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** 获取数据库连接 
	 * @throws SQLException */
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/** 获取结果集 
	 * @throws SQLException */
	public <T> List<T> getBeanList(StringBuffer sql,Class<T> bean,Object... params){
		QueryRunner qr = null;
		List<T> beanList = null;
		Connection conn = null;
		
		try {
			qr = new QueryRunner();
			conn = getConnection();
			beanList = (List<T>)qr.query(conn, sql.toString(),new BeanListHandler<T>(bean),params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//释放数据库资源
		finally {  
			if(conn != null) DbUtils.closeQuietly(conn);  
        }
		
		return beanList;
	}
	
	/** 获取结果集 
	 * @throws SQLException */
	public List<Map<String, Object>> getBeanListByMap(StringBuffer sql,Object... params){
		QueryRunner qr = null;
		List<Map<String, Object>> mapList = null;
		Connection conn = null;
		
		try {
			qr = new QueryRunner();
			conn = getConnection();
			mapList = qr.query(conn, sql.toString(),new MapListHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//释放数据库资源
		finally {  
			if(conn != null) DbUtils.closeQuietly(conn);  
		}
		
		return mapList;
	}
	
	/** 获取结果集 
	 * @throws SQLException */
	public Integer merge(StringBuffer sql,Object... params){
		QueryRunner qr = null;
		Connection conn = null;
		int updateCnt = 0;
		
		try {
			qr = new QueryRunner();
			conn = getConnection();
			updateCnt = qr.update(conn,sql.toString(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//释放数据库资源
		finally {  
			if(conn != null) DbUtils.closeQuietly(conn);  
		}
		
		return updateCnt;
	}
}
