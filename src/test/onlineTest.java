package test;

import java.sql.*;

public class onlineTest {
 public static void main(String [] args)
 {
  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=UniversityDB";
  String userName="sa";
  String userPwd="3063603";
 try
{
	Class.forName(driverName);
	System.out.println("���������ɹ���");
}catch(Exception e){
	e.printStackTrace();
	System.out.println("��������ʧ�ܣ�");
}
try{
	Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		System.out.println("�������ݿ�ɹ���");
}catch(Exception e)
{
	e.printStackTrace();
	System.out.print("SQL Server����ʧ�ܣ�");
}		
}
}