package com.javaEE.code.class12.servlet;

import com.javaEE.code.class12.jdbc.StudentJDBC;
import com.javaEE.code.class12.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

// 教师看到学生列表
@WebServlet(name = "StudentServlet", urlPatterns = "/Student")
public class StudentServlet extends HttpServlet {
    private StudentJDBC studentJDBC= new StudentJDBC();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentJDBC.selectAll();
        req.setAttribute("studentList", studentList);

        req.getRequestDispatcher("/jsp/teacher.jsp").forward(req, resp);
    }

    // https://www.cnblogs.com/xiaochuan94/p/9184444.html
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 获取方法名
        String name = req.getParameter("method");

        if(name == null || name.isEmpty()){
            throw new RuntimeException("method parameter does not exist");
        }

        // 获得当前类的Class对象
        Class c = this.getClass();
        Method method = null;
        try {
            // 使用反射机制获取在本类中声明了的方法
            method =  getClass().getDeclaredMethod(name, HttpServletRequest.class, HttpServletResponse.class);
            // 反射调用方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentJDBC.addStudent(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }

    protected void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentJDBC.updateStudent(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }
}
