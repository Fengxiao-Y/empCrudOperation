package com.yidu.d280.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能描述：数据库连接工具类，实现Java连接到数据库，及关闭连接
 * @author t262
 * 日期：2018年10月29日 上午10:33:59
 * version:1.0
 */
public class JdbcUtils {
	//数据库驱动程序
	private static String driver="com.mysql.cj.jdbc.Driver";
	//连接字符串
	private static String url="jdbc:mysql://localhost:3306/d280db?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
	//用户名
	private static String user="root";
	//连接密码
	private static String password="Yanjin";
	
	//静态块，实现类加载时，加载数据库的驱动程序
	static{
		try {
			//注册驱动程序
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 */
	private JdbcUtils(){
		
	}
	
	/**
	 * 数据库连接方法
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * 释放数据库连接、语句对象、结果集对象资源
	 * @param rs 结果集对象
	 * @param pstmt 语句对象
	 * @param conn 数据库连接对象
	 */
	public static void close(ResultSet rs,Statement pstmt,Connection conn){
		try {
			//如果结果集对象不为空，则关闭
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//如果语句对象不为空，则关闭
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//如果连接对象不为空，则关闭
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
