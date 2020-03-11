package com.javaEE.code.class12.servlet;

import com.javaEE.code.class12.jdbc.HomeworkJDBC;
import com.javaEE.code.class12.model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

// 学生看到的作业列表，仅可提交作业
@WebServlet(name = "HomeworkStudentServlet", urlPatterns = "/HomeworkStudent")
public class HomeworkStudentServlet extends HttpServlet {
    private HomeworkJDBC homeworkJDBC = new HomeworkJDBC();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkJDBC.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/jsp/homework-student.jsp").forward(req, resp);
    }


}
