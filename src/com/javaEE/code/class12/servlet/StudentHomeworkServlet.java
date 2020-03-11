package com.javaEE.code.class12.servlet;

import com.javaEE.code.class12.jdbc.StudentHomeworkJDBC;
import com.javaEE.code.class12.model.Homework;
import com.javaEE.code.class12.model.Student;
import com.javaEE.code.class12.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentHomeworkServlet", urlPatterns = "/StudentHomework")
public class StudentHomeworkServlet extends HttpServlet {
    private StudentHomeworkJDBC studentHomeworkJDBC = new StudentHomeworkJDBC();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = studentHomeworkJDBC.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/jsp/studentHomework.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        StudentHomework sh = new StudentHomework();
        sh.setStudentId(Long.parseLong(req.getParameter("studentId")));
        sh.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
        sh.setHomeworkTitle(req.getParameter("title"));
        sh.setHomeworkContent(req.getParameter("content"));
        System.out.println(sh.toString());
        studentHomeworkJDBC.addStudentHomework(sh);
        // 刷新页面
//        doGet(req, resp);
        resp.sendRedirect("HomeworkStudent");
    }
}
