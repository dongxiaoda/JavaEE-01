<%@ page import="com.javaEE.code.class12.model.StudentHomework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaEE.code.class12.jdbc.StudentHomeworkJDBC" %>
<%@ page import="com.javaEE.code.class12.model.Homework" %>
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
  <title>student</title>
  <style>
    #div4, #div5 {
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
<h3 align="center">作业列表</h3>
<div>
  <table align="center" width="960" border="1" cellpadding="1" cellspacing="1">
    <tr align="center" height="50" bgcolor="gray">
      <td>作业ID</td>
      <td>作业标题</td>
      <td>作业内容</td>
      <td>创建时间</td>
      <td>更新时间</td>
    </tr>
    <%
      // 通过网络请求获取结果
      List<Homework> list = (List<Homework>) request.getAttribute("list");
      // 通过HomeworkJDBC对象获取结果
//    HomeworkJDBC shJDBC = new HomeworkJDBC();
//    List<Homework> list = hJDBC.selectAll();
      for (Homework h : list) {
    %>
    <tr align="center" bgcolor="white" height="30">
      <td><%=h.getId()%>
      </td>
      <td><%=h.getHomeworkTittle()%>
      </td>
      <td><%=h.getHomeworkContent()%>
      </td>
      <td><%=h.getCreateTime()%>
      </td>
      <td><%=h.getUpdateTime()%>
      </td>
    </tr>
    <%
      }
    %>
  </table>
</div>
<div align="center">
  <div id="div4">
    <h3 align="center">添加作业</h3>
    <div align="center">
      <form action="HomeworkTeacher?method=addHomework" method="post" onsubmit="location.reload()">
        <table>
          <tr>
            <td align="right">作业标题:&ensp;&ensp;</td>
            <td><input type="text" name="title"></td>
          </tr>
          <tr>
            <td valign="right">作业内容:&ensp;&ensp;</td>
            <td><input type="text" name="content"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>
              <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
              <input type="submit" value="发布作业"/>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <div id="div5">
    <h3 align="center">修改作业</h3>
    <div align="center">
      <form action="HomeworkTeacher?method=updateHomework" method="post" onsubmit="location.reload()">
        <table>
          <tr>
            <td align="right">作业ID:&ensp;&ensp;</td>
            <td><input type="text" name="homeworkId"></td>
          </tr>
          <tr>
            <td align="right">作业标题:&ensp;&ensp;</td>
            <td><input type="text" name="title"></td>
          </tr>
          <tr>
            <td valign="right">作业内容:&ensp;&ensp;</td>
            <td><input type="text" name="content"></td>
          </tr>
          <tr>
            <td><br></td>
            <td><br></td>
          </tr>
          <tr>
            <td></td>
            <td>
              <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&nbsp;
              <input type="submit" value="修改作业"/>
            </td>
          </tr>
        </table>

      </form>
    </div>
  </div>
  <input type="button" name="Submit" onclick="history.back();" value="返回上一页">&ensp;
  <button onclick="location.href='index.jsp'" type="button">返回首页</button>&ensp;
  <button onclick="location.href='StudentHomework'" type="button">查看作业提交列表</button>&ensp;
  <button onclick="location.href='Student'" type="button">查看学生列表</button>&ensp;
</div>

</body>
</html>
