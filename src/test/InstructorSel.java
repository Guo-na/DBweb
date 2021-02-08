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
	//1.注册数据库驱动。通过静态代码块来编写
		static{
			 try
				{
					Class.forName(driverName);
					System.out.println("加载驱动成功！");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("加载驱动失败！");
				}
		}
		//2.获取数据库连接
		public static Connection getConnection(){
			try{
				Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
					System.out.println("连接数据库成功！");
				return dbConn;
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.print("SQL Server连接失败！");
			}	
			return null;
		}
	 
		//3.获取数据库连接对象
		public static Statement getStatement() {
		    Statement st = null;
		    try {
		      st = getConnection().createStatement();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return st;
		}
		//6.关闭资源
		public static void release(Connection conn, Statement st, ResultSet rs){
		    //关闭数据库连接资源
		    if(rs != null){
		        try {
		            rs.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	 
		    //关闭数据库连接对象资源
		    if(st != null){
		        try {
		            st.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	 
		    //关闭查询结果资源
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
		System.out.println("学号:  "+studentID);
		String execuSql = "{call teacherCourse(?)}";
		/* request.setAttribute("user", user); */
		CallableStatement call = null;
		Connection  dbConn2 = null;
		try{
			 dbConn2=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("连接数据库成功！");
			call = dbConn2.prepareCall(execuSql);
			call.setString(1, studentID);
			Statement stmt = dbConn2.createStatement();
			String sql;
			/*
			 * sql = "SELECT ID, name FROM student";
			 * 
			 * ResultSet rs = stmt.executeQuery(sql);
			 */
			 
           //得到和处理结果集
			/*
			 * while(rs.next()){ //检索 int id = rs.getInt("ID"); String name =
			 * rs.getString("name");
			 * 
			 * //显示 System.out.print("ID: " + id); System.out.print(", Name: " + name);
			 * System.out.println(); }
			 */
           call.execute();
			
		  ResultSet rss = (ResultSet)call.getResultSet();
		  System.out.println(call.getUpdateCount());
		  List<schedule> list = new ArrayList<schedule>();
		  while(rss.next()) { 
			  String ID = rss.getString(3); System.out.println("学号:  "+ID);
			  String sec_id = rss.getString(4); System.out.println("节:  "+sec_id);
			  String semester = rss.getString(5); System.out.println("学季:  "+semester);
			  String year = rss.getString(6); System.out.println("学年:  "+year);
			  String building = rss.getString(7); System.out.println("学生名字:  "+building);
			  String classroom = rss.getString(8); System.out.println("班级:  "+classroom); 
			  String time_slot = rss.getString(9); System.out.println("总学分:  "+time_slot); 
			  String instructor_id = rss.getString(1); System.out.println("专业:  "+instructor_id);
			  String instructor_name = rss.getString(2); System.out.println("课程ID:  "+instructor_name);
			   
			  
			  
			  String grade = rss.getString(10); System.out.println("成绩:  "+grade); 
				

				 
			  schedule stu=new schedule(ID,sec_id,semester,year,building,classroom,time_slot,instructor_id,instructor_name);
			  list.add(stu);
		  }
		 request.setAttribute("list", list);
		 System.out.println(call.getMoreResults()); 
				/*
				 * System.out.println(call.getString(5));
				 */

         //清理环境
           rss.close();
           stmt.close();
           dbConn2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
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
