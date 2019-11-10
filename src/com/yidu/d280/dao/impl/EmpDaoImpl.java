package com.yidu.d280.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.d280.dao.EmpDao;
import com.yidu.d280.domain.Emp;
import com.yidu.d280.utils.JdbcUtils;

public class EmpDaoImpl implements EmpDao {

	@Override
	public int add(Emp emp) {
		int rows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="insert into emp(empNo,empName,"
					+ "job,manager,hiredate,salary,commision,"
					+ "deptno) values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getManager());
			//将字符串转换成日期数据
			pstmt.setDate(5, java.sql.Date.valueOf(emp.getHiredate()));
			pstmt.setDouble(6, emp.getSalary());
			pstmt.setDouble(7, emp.getCommision());
			pstmt.setInt(8, emp.getDeptno());
			
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(null, pstmt, conn);
		}
		return rows;
	}

	@Override
	public int delete(int empNo) {
		int rows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="delete from emp where empNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(null, pstmt, conn);
		}
		return rows;
	}

	@Override
	public int update(Emp emp) {
		int rows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="update emp set empName=?,job=?,"
					+ "manager=?,hiredate=?,salary=?,"
					+ "commision=?,deptno=? where empNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getManager());
			//将字符串转换成日期数据
			pstmt.setDate(4, java.sql.Date.valueOf(emp.getHiredate()));
			//System.out.println("传递过来的入职日期："+emp.getHiredate());
			//System.out.println("转换后的入职日期："+java.sql.Date.valueOf(emp.getHiredate()));
			pstmt.setDouble(5, emp.getSalary());
			pstmt.setDouble(6, emp.getCommision());
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpNo());
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(null, pstmt, conn);
		}
		return rows;
	}

	@Override
	public Emp findById(int empNo) {
		Emp emp=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from emp where empNo=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				emp=new Emp();
				emp.setEmpNo(rs.getInt("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setJob(rs.getString("job"));
				emp.setManager(rs.getInt("manager"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHiredate(sdf.format(rs.getDate("hiredate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptno(rs.getInt("deptno"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return emp;
	}

	@Override
	public List<Emp> findAll() {
		return null;
	}

	@Override
	public List<Emp> findByPage(int rows, int page) {
		List<Emp> empList=new ArrayList<Emp>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from emp limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Emp emp=new Emp();
				emp.setEmpNo(rs.getInt("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setJob(rs.getString("job"));
				emp.setManager(rs.getInt("manager"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHiredate(sdf.format(rs.getDate("hiredate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return empList;
	}

	@Override
	public int count() {
		int rows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select count(*) from emp";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}

	@Override
	public List<Emp> findByPage(int rows, int page, String condition) {
		List<Emp> empList=new ArrayList<Emp>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from emp "+condition+" limit ?,? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Emp emp=new Emp();
				emp.setEmpNo(rs.getInt("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setJob(rs.getString("job"));
				emp.setManager(rs.getInt("manager"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHiredate(sdf.format(rs.getDate("hiredate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return empList;
	}

	@Override
	public int count(String condition) {
		int rows=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select count(*) from emp "+condition;
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}

}
