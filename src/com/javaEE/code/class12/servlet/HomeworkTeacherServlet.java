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

// 教师看到的作业列表，可进行添加和修改操作
@WebServlet(name = "HomeworkTeacherServlet", urlPatterns = "/HomeworkTeacher")
public class HomeworkTeacherServlet extends HttpServlet {
    private HomeworkJDBC homeworkJDBC = new HomeworkJDBC();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkJDBC.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/jsp/homework-teacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // 获取方法名
        String name = req.getParameter("method");

        if (name == null || name.isEmpty()) {
            throw new RuntimeException("method parameter does not exist");
        }

        // 获得当前类的Class对象
        Class c = this.getClass();
        Method method = null;
        try {
            // 使用反射机制获取在本类中声明了的方法
            method = getClass().getDeclaredMethod(name, HttpServletRequest.class, HttpServletResponse.class);
            // 反射调用方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void addHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework();
        h.setHomeworkTittle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        System.out.println(h.toString());
        homeworkJDBC.addHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

    protected void updateHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework();
        h.setId(Long.parseLong(req.getParameter("homeworkId")));
        h.setHomeworkTittle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        homeworkJDBC.updateHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }
}
