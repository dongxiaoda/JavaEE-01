<%@ page import="com.javaEE.code.class12.model.StudentHomework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaEE.code.class12.jdbc.StudentHomeworkJDBC" %>

<%--
  Created by IntelliJ IDEA.
  User: wkm
  Date: 2020/2/27
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>My Homework</title>
</head>
<body>
<h2 align="center">学生作业管理系统——教师端</h2>
<hr color="black">
<h3 align="center">作业提交列表</h3>
<div align="center">
  <table align="center" width="960" border="1"
         bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="gray" height="50">
      <td>ID</td>
      <td>学生学号</td>
      <td>作业编号</td>
      <td>作业标题</td>
      <td>作业内容</td>
      <td>创建时间</td>
    </tr>
    <%
      StudentHomeworkJDBC studentHomeworkJDBC = new StudentHomeworkJDBC();
      List<StudentHomework> list = studentHomeworkJDBC.selectAll();
      for (StudentHomework sh : list) {
    %>
    <tr align="center" bgcolor="white" height="30">
      <td><%=sh.getId()%>
      </td>
      <td><%=sh.getStudentId()%>
      </td>
      <td><%=sh.getHomeworkId()%>
      </td>
      <td><%=sh.getHomeworkTitle()%>
      </td>
      <td><%=sh.getHomeworkContent()%>
      </td>
      <td><%=sh.getCreateTime()%>
      </td>
    </tr>
    <%
      }
    %>
  </table>
  <br>
  <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
  <button onclick="location.href='index.jsp'" type="button">返回首页</button>&ensp;
  <button onclick="location.href='Student'" type="button">查看学生列表</button>&ensp;
  <button onclick="location.href='HomeworkTeacher'" type="button">查看作业发布列表</button>&ensp;
</div>

</body>
</html>