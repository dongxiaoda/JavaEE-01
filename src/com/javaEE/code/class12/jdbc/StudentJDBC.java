package com.javaEE.code.class12.jdbc;

import com.javaEE.code.class12.DBCP;
import com.javaEE.code.class12.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentJDBC {

    // 数据库连接
    private Connection connection = null;
    // 执行对象
    private PreparedStatement pstmt = null;

    // 查找所有记录
    public List<Student> selectAll() {
        // 定义 SQL 语句
        String sql = "SELECT * FROM s_student";
        System.out.println(sql);
        // 一条结果
        Student s = null;
        // 存储结果集
        List<Student> resultList = new ArrayList<>();
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery(sql);
            // 输出结果集
            while (resultSet.next()) {
                // 初始化一条结果
                s = new Student();
                s.setId(resultSet.getLong("id"));
                s.setName(resultSet.getString("name"));
                s.setCreateTime(resultSet.getTimestamp("create_time"));
                s.setUpdateTime(resultSet.getTimestamp("update_time"));
                resultList.add(s);
                // 归还连接
                DBCP.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // 添加一条记录
    public void addStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "INSERT INTO s_student(id, name) VALUES (?, ?)";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, s.getId());
            pstmt.setString(2, s.getName());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除一条记录
    public void deleteStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "DELETE FROM s_student WHERE id=?";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, s.getId());
            System.out.println(sql);
            pstmt.execute();
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新一条记录
    public void updateStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "UPDATE s_student SET name=?, update_time=? WHERE id=?";
        //
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, s.getName());
            pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
            pstmt.setLong(3, s.getId());
            System.out.println(sql);
            pstmt.execute();
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        StudentJDBC sJDBC = new StudentJDBC();
//        Student s = new Student();

    // To test addStudent(Student s)
//        s.setId(100L);
//        s.setName("ddd");
//        sJDBC.addStudent(s);

    // To test selectAll()
//        List<Student> list =  sJDBC.selectAll();
//        for(Student student : list)
//            System.out.println(student.getName());

    // To test deleteStudent(Student s)
//        s.setId(100L);
//        s.setName("ddd");
//        sJDBC.deleteStudent(s);

    // To test updateStudent(Student s)
//        s.setId(100L);
//        s.setName("dd");
//        sJDBC.updateStudent(s);

//        sJDBC.closeAll();
//    }
}
