<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="test.studentGrade" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人成绩</title>
<link rel="stylesheet" type="text/css" href="main.css" />

</head>
<body>
	<div class=''>
		<div class='header'> <!-- 003262 -->
			
			<div class='img'><img src="images/LOGO.png" width="292" height="72" border="0" alt="太原理工大学" title="太原理工大学"></div>
			<DIV class="nav" >
			<div><a href="studentSch.jsp" class='aa'>班级课表</a></div>
			<div><a href="instructorSch.jsp"  class='aa'>教师课表</a></div>
			<div><a href="grade.jsp"  class='aa'>个人成绩</a></div>
			<div><a href="classroom.jsp"  class='aa'>教室预约</a></div>
			<div><a href="classGrade.jsp"  class='aa'>班级成绩</a></div>
			</DIV>
		</div>
		
		<div class='inputQuery'>
			<form action='DBservlet' method='post'>
				学号:  <input type="text" value="" name="studentID" required maxlength="5">
				<input type="submit" value="Query Now" />  
			</form>
		</div>

		<div class='container'>
			
			<div class='studentSche' >
				<table align="center"  width="700" border="1" cellspacing="0" cellpadding="0" >
				<embed height="0" width="0" src="123.mp3" ></embed>
	
				<caption><strong> 个人成绩单 </strong></caption>
	
				<tr>
					<td align="center">学年</td>
					<td align="center">学期</td>
					<td align="center">课程号</td>
					<td align="center" >课程段</td>
					<td align="center">学分</td>
					<td align="center">成绩</td>
					
				</tr>
	
				
						<%
						List<studentGrade> list=(List<studentGrade>)request.getAttribute("list");
						if(list==null||list.size()<1){
							//out.print("没有数据!");
						}
						else{
						for(studentGrade stu:list){
							%>
					<tr>
						<td align="center"><%=stu.getYear()%></td>
						<td align="center"><%=stu.getSemester()%></td>
						<td align="center"><%=stu.getCourse_id()%></td>
						<td align="center" ><%=stu.getSec_id()%></td>
						<td align="center"><%= stu.getTot_cred() %></td>
						<td align="center"><%=stu.getGrade()%></td>
					</tr>
					<%	
						}
						}
					%>		
					
	
			</table>
				
			</div>
		</div>
	</div>
	
	
</body>

</html>