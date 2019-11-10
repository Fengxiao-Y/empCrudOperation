package com.yidu.d280.dao;

import java.util.List;

import com.yidu.d280.domain.Emp;

/**
 * 针对Emp表进行操作的数据层接口
 * @author Administrator
 *
 */
public interface EmpDao {
	/**
	 * 添加
	 * @param emp
	 * @return
	 */
	public int add(Emp emp);
	/**
	 * 删除
	 * @param empNo
	 * @return
	 */
	public int delete(int empNo);
	/**
	 * 修改
	 * @param emp
	 * @return
	 */
	public int update(Emp emp);
	
	/**
	 * 根据员工编号查找单个员工信息
	 * @param empNo
	 * @return
	 */
	public Emp findById(int empNo);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Emp> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有员工信息集合
	 */
	public List<Emp> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<Emp> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return
	 */
	public int count(String condition);
}
