package com.yidu.d280.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.d280.dao.EmpDao;
import com.yidu.d280.dao.impl.EmpDaoImpl;
import com.yidu.d280.domain.Emp;


/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		
		String method=request.getParameter("method");
		System.out.println("method:"+method);
		if("findAll".equals(method)){
			this.findAll(request,response);
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ�����Ա������ַ���
		String empNoStr=request.getParameter("empNoStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] empNos=empNoStr.split(",");
		
		//�������ݲ��������
		EmpDao empDao=new EmpDaoImpl();
		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<empNos.length;i++){
				//��ȡ��ǰ���ַ���
				String tempNo=empNos[i];
				//���ַ������ת��Ϊ���͵�Ա�����
				int empNo=Integer.parseInt(tempNo);
				//ִ��ɾ������
				empDao.delete(empNo);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		
		//�ر��������
		out.close();
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		//�������Կͻ��˵�����
		int empNo=Integer.parseInt(request.getParameter("empNo"));
		String empName=request.getParameter("empName");
		String job=request.getParameter("job");
		int manager=Integer.parseInt(request.getParameter("manager"));
		String hiredate=request.getParameter("hiredate");
		double salary=Double.parseDouble(request.getParameter("salary"));
		double commision=Double.parseDouble(request.getParameter("commision"));
		int deptno=Integer.parseInt(request.getParameter("deptno"));
		
		//���ݷ�װ�ɶ���
		Emp emp=new Emp(empNo, empName, job, manager, hiredate, salary, commision, deptno);
		
		//�������ݲ����ִ����ӷ���
		EmpDao empDao=new EmpDaoImpl();
		int rows=empDao.update(emp);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		out.close();
	}

	/**
	 * ����
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//System.out.println("1111");
		//�������Կͻ��˵�����
		int empNo=Integer.parseInt(request.getParameter("empNo"));
		String empName=request.getParameter("empName");
		String job=request.getParameter("job");
		int manager=Integer.parseInt(request.getParameter("manager"));
		String hiredate=request.getParameter("hiredate");
		double salary=Double.parseDouble(request.getParameter("salary"));
		double commision=Double.parseDouble(request.getParameter("commision"));
		int deptno=Integer.parseInt(request.getParameter("deptno"));
		
		
		//���ݷ�װ�ɶ���
		Emp emp=new Emp(empNo, empName, job, manager, hiredate, salary, commision, deptno);
		//System.out.println("2222");
		//�������ݲ����ִ����ӷ���
		EmpDao empDao=new EmpDaoImpl();
		int rows=empDao.add(emp);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
			//System.out.println("3333");
		}else{
			out.print("failure");
			//System.out.println("4444");
		}
		
		out.close();
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		/*
		//�õ����������е���������
		String empName=request.getParameter("empName");
		String job=request.getParameter("job");
		String deptno=request.getParameter("deptno");
		
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(empName!=null && !empName.equals("")){
			condition=condition+" and empName like '%"+empName+"%' ";
		}
		
		if(job!=null && !job.equals("")){
			condition=condition+" and job like '%"+job+"%' ";
		}
		
		if(deptno!=null && !deptno.equals("")){
			//����deptno��int���ͣ�����ת������
			int dept=Integer.parseInt(deptno);
			//��ӵ�������
			condition=condition+" and deptno="+dept+" ";
		}
		
		System.out.println("condition="+condition);
		*/
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�ִ�з�ҳ��ѯ
		EmpDao empDao=new EmpDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Emp> empList=empDao.findByPage(rows, page);
		//��ѯ��emp����ܼ�¼��
		int totalRows=empDao.count();
		
		//A��ʽ��
		/*
		//�����ݼ���ת����JSON���ݸ�ʽ
		String jsonStr=JSONArray.fromObject(empList).toString();
		//���ɸ�ʽ������
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		*/
		
		//B:��ʽ
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", empList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//System.out.println(jsonData);
		out.close();
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
