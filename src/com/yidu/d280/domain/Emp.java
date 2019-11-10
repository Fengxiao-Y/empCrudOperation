package com.yidu.d280.domain;

import java.io.Serializable;

public class Emp implements Serializable{
	private static final long serialVersionUID = -6220734961811174880L;
	private int empNo;
	private String empName;
	private String job;
	private int manager;
	private String hiredate;
	private double salary;
	private double commision;
	private int deptno;
	
	public Emp() {
	}
	
	public Emp(int empNo, String empName, String job, int manager, String hiredate, double salary, double commision,
			int deptno) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.manager = manager;
		this.hiredate = hiredate;
		this.salary = salary;
		this.commision = commision;
		this.deptno = deptno;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}
