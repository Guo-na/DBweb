<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级成绩</title>
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
			<form action='Clservlet' method='post'>
				课程号:<input type="text" value="" name="CourseID" required maxlength="10"><br>
				课程段:<input type="text" value="" name="SecID" required maxlength="5"><br>
				班号:<input type="text" value="" name="ClassID" required maxlength="5"><br>
				<input type="submit" value="Query Now" />  
			</form>
		</div>
		<div class='container'>
		<div class='studentSche'>
			<table align="center"  width="700" border="1" cellspacing="0" cellpadding="0" >
			<embed height="0" width="0" src="123.mp3" ></embed>

			<caption><strong> 班级成绩单 </strong></caption>

			<tr >
				<td align="center">最高分</th>
				<td align="center">最低分</th>
				<td align="center">平均分</th>
				<td align="center">总分</th>
				<td align="center">不及格人数</th>
				<td align="center">总人数</th>
			</tr>
			<% 
				String max=(String)request.getAttribute("max");
				String min=(String)request.getAttribute("min");
				String avg=(String)request.getAttribute("avg");
				String total=(String)request.getAttribute("total");
				String failure=(String)request.getAttribute("failure");
				String totnum=(String)request.getAttribute("totnum");
			%>
			<tr>
				<td align="center"><%=max %></th>
				<td align="center"><%=min %></th>
				<td align="center"><%=avg %></th>
				<td align="center"><%=total %></th>
				<td align="center"><%=failure %></th>
				<td align="center"><%=totnum %></th>
			</tr>

		</table>
			
		</div>
		</div>
	
	
	</div>
</body>

</html>