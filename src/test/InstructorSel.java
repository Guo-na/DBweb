package test;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import test.schedule;
/**
 * Servlet implementation class DBservlet
 */
@WebServlet("/InstructorSel")
public class InstructorSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=UniversityDB";
	private static  String userName="sa";
	private static  String userPwd="3063603";
	//1.ע�����ݿ�������ͨ����̬���������д
		static{
			 try
				{
					Class.forName(driverName);
					System.out.println("���������ɹ���");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("��������ʧ�ܣ�");
				}
		}
		//2.��ȡ���ݿ�����
		public static Connection getConnection(){
			try{
				Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					System.out.println("�������ݿ�ɹ���");
				return dbConn;
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.print("SQL Server����ʧ�ܣ�");
			}	
			return null;
		}
	 
		//3.��ȡ���ݿ����Ӷ���
		public static Statement getStatement() {
		    Statement st = null;
		    try {
		      st = getConnection().createStatement();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return st;
		}
		//6.�ر���Դ
		public static void release(Connection conn, Statement st, ResultSet rs){
		    //�ر����ݿ�������Դ
		    if(rs != null){
		        try {
		            rs.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	 
		    //�ر����ݿ����Ӷ�����Դ
		    if(st != null){
		        try {
		            st.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	 
		    //�رղ�ѯ�����Դ
		    if(conn != null){
		        try {
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorSel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String studentID = request.getParameter("studentID");
		System.out.println("ѧ��:  "+studentID);
		String execuSql = "{call teacherCourse(?)}";
		/* request.setAttribute("user", user); */
		CallableStatement call = null;
		Connection  dbConn2 = null;
		try{
			 dbConn2=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("�������ݿ�ɹ���");
			call = dbConn2.prepareCall(execuSql);
			call.setString(1, studentID);
			Statement stmt = dbConn2.createStatement();
			String sql;
			/*
			 * sql = "SELECT ID, name FROM student";
			 * 
			 * ResultSet rs = stmt.executeQuery(sql);
			 */
			 
           //�õ��ʹ�������
			/*
			 * while(rs.next()){ //���� int id = rs.getInt("ID"); String name =
			 * rs.getString("name");
			 * 
			 * //��ʾ System.out.print("ID: " + id); System.out.print(", Name: " + name);
			 * System.out.println(); }
			 */
           call.execute();
			
		  ResultSet rss = (ResultSet)call.getResultSet();
		  System.out.println(call.getUpdateCount());
		  List<schedule> list = new ArrayList<schedule>();
		  while(rss.next()) { 
			  String ID = rss.getString(3); System.out.println("ѧ��:  "+ID);
			  String sec_id = rss.getString(4); System.out.println("��:  "+sec_id);
			  String semester = rss.getString(5); System.out.println("ѧ��:  "+semester);
			  String year = rss.getString(6); System.out.println("ѧ��:  "+year);
			  String building = rss.getString(7); System.out.println("ѧ������:  "+building);
			  String classroom = rss.getString(8); System.out.println("�༶:  "+classroom); 
			  String time_slot = rss.getString(9); System.out.println("��ѧ��:  "+time_slot); 
			  String instructor_id = rss.getString(1); System.out.println("רҵ:  "+instructor_id);
			  String instructor_name = rss.getString(2); System.out.println("�γ�ID:  "+instructor_name);
			   
			  
			  
			  String grade = rss.getString(10); System.out.println("�ɼ�:  "+grade); 
				

				 
			  schedule stu=new schedule(ID,sec_id,semester,year,building,classroom,time_slot,instructor_id,instructor_name);
			  list.add(stu);
		  }
		 request.setAttribute("list", list);
		 System.out.println(call.getMoreResults()); 
				/*
				 * System.out.println(call.getString(5));
				 */

         //������
           rss.close();
           stmt.close();
           dbConn2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
		}finally {
			DBservlet.release(dbConn2,call,null);
		}
		request.getRequestDispatcher("instructorSch.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
