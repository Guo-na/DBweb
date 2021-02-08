package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Clservlet
 */
@WebServlet("/Clservlet")
public class Clservlet extends HttpServlet {
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
    public Clservlet() {
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
		String CourseID = request.getParameter("CourseID");
		String SecID=request.getParameter("SecID");
		String ClassID=request.getParameter("ClassID");
		System.out.println("�γ̺�:  "+CourseID);
		System.out.println("�γ̶�:  "+SecID);
		System.out.println("�༶��:  "+ClassID);
		String execuSql = "{call classScore(?,?,?)}";
		/* request.setAttribute("user", user); */
		CallableStatement call = null;
		Connection  dbConn2 = null;
		try{
			 dbConn2=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("�������ݿ�ɹ���");
			call = dbConn2.prepareCall(execuSql);
			call.setString(1, CourseID);
			call.setString(2, SecID);
			call.setString(3, ClassID);
			Statement stmt = dbConn2.createStatement();
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
		  List<studentGrade> list = new ArrayList<studentGrade>();
		  while(rss.next()) { 
			  String max = rss.getString(1); System.out.println(max);
			  String min = rss.getString(2); System.out.println(min);
			  String avg = rss.getString(3); System.out.println(avg);
			  String total = rss.getString(4); System.out.println(total);
			  String fail = rss.getString(5); System.out.println(fail);
			  String totalnum = rss.getString(6); System.out.println(totalnum);
			  request.setAttribute("max", max);
			  request.setAttribute("min", min);
			  request.setAttribute("avg", avg);
			  request.setAttribute("total", total);
			  request.setAttribute("failure", fail);
			  request.setAttribute("totnum", totalnum);
				/*
				 * String room_number = rss.getString(11);
				 * System.out.println("���Һ�:  "+room_number); String building =
				 * rss.getString(12); System.out.println("��ѧ¥:  "+building); String time_slot_id
				 * = rss.getString(13); System.out.println("�Ͽ�ʱ��:  "+time_slot_id); String i_ID
				 * = rss.getString(14); System.out.println("��ʦ����:  "+i_ID); String i_name =
				 * rss.getString(15); System.out.println("��ʦ����:  "+i_name);
				 */
		  }
		 

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
		request.getRequestDispatcher("classGrade.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
