package com.yidu.d280.dao;

import java.util.List;

import com.yidu.d280.domain.Emp;

/**
 * ���Emp����в��������ݲ�ӿ�
 * @author Administrator
 *
 */
public interface EmpDao {
	/**
	 * ���
	 * @param emp
	 * @return
	 */
	public int add(Emp emp);
	/**
	 * ɾ��
	 * @param empNo
	 * @return
	 */
	public int delete(int empNo);
	/**
	 * �޸�
	 * @param emp
	 * @return
	 */
	public int update(Emp emp);
	
	/**
	 * ����Ա����Ų��ҵ���Ա����Ϣ
	 * @param empNo
	 * @return
	 */
	public Emp findById(int empNo);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Emp> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<Emp> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<Emp> findByPage(int rows,int page,String condition);

	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	public int count();
	
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
	 */
	public int count(String condition);
}
