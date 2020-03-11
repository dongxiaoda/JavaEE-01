<%@ page import="com.javaEE.code.class12.model.StudentHomework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaEE.code.class12.jdbc.StudentHomeworkJDBC" %>
<%@ page import="com.javaEE.code.class12.model.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: 董金达
  Date: 2020/3/8
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>teacher</title>
  <style>
    #div2, #div3 {
      width: 50%;
      float: left;
      /*border: black;*/
      text-align: center;
    }
  </style>
</head>
<body>
<h2 align="center">学生作业管理系统——教师端</h2>
<hr color="black">
<h3 align="center">学生列表</h3>
<div align="center">
  <div>
    <table align="center" width=70% border="1" cellpadding="1" cellspacing="1">
      <tr align="center" height="50" bgcolor="gray">
        <td>学生ID</td>
        <td>学生姓名</td>
        <td>创建时间</td>
        <td>更新时间</td>
      </tr>
      <%
        // 通过网络请求获取结果
        List<Student> list = (List<Student>) request.getAttribute("studentList");
        // 通过StudentHomeworkJDBC对象获取结果
//    StudentHomeworkJDBC shJDBC = new StudentHomeworkJDBC();
//    List<StudentHomework> list = shJDBC.selectAll();
        for (Student s : list) {
      %>
      <tr align="center" bgcolor="white" height="30">
        <td><%=s.getId()%>
        </td>
        <td><%=s.getName()%>
        </td>
        <td><%=s.getCreateTime()%>
        </td>
        <td><%=s.getUpdateTime()%>
        </td>
      </tr>
      <%
        }
      %>
    </table>
  </div>
  <div>
    <div id="div2">
      <h3>添加学生信息</h3>
      <form action="Student?method=addStudent" method="post" onsubmit="location.reload()">
        <table align="center">
          <tr>
            <td align="right">学生ID:&ensp;&ensp;</td>
            <td><input type="text" name="studentId"></td>
          </tr>
          <tr>
            <td align="right">学生姓名:&ensp;&ensp;</td>
            <td><input type="text" name="studentName"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>

              <input type="submit" value="添加学生信息"/>
            </td>
          </tr>
        </table>
      </form>
    </div>
    <div id="div3">
      <h3>更新学生信息</h3>
      <form action="Student?method=updateStudent" method="post" onsubmit="location.reload()">
        <table align="center">
          <tr>
            <td align="right">学生ID:&ensp;&ensp;</td>
            <td><input type="text" name="studentId"></td>
          </tr>
          <tr>
            <td align="right">学生姓名:&ensp;&ensp;</td>
            <td><input type="text" name="studentName"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>
              <input type="submit" value="更新学生信息"/>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
  <button onclick="location.href='index.jsp'" type="button">返回首页</button>&ensp;
  <button onclick="location.href='StudentHomework'" type="button">查看作业提交列表</button>&ensp;
  <button onclick="location.href='HomeworkTeacher'" type="button">查看作业发布列表</button>&ensp;
</div>
</body>
</html>
