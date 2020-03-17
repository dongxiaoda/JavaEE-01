<%@ page import="com.javaEE.code.class12.model.StudentHomework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaEE.code.class12.jdbc.StudentHomeworkJDBC" %>
<%--
  Created by IntelliJ IDEA.
  User: 董金达
  Date: 2020/2/27
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>JavaEE-01</title>
  <style>
    #div1 {
      height: 100%;
      width: 50%;
      /*float: none;*/
      /*border: black;*/
      margin: 0 auto;
      text-align: center;
    }
  </style>
</head>
<body>
<h2 align="center">基于 Java + Servlet + MySQL 的简易学生作业管理系统</h2>
<div id="div1" align="center">
  <fieldset>
    <legend align="left">学生作业管理系统</legend>
    <button onclick="location.href='HomeworkStudent'" type="button">学生登录</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <button onclick="location.href='Student'" type="button">教师登录</button>
  </fieldset>
</div>
</button>
</body>
</html>
