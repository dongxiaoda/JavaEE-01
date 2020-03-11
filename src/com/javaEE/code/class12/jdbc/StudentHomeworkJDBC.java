package com.javaEE.code.class12.jdbc;

import com.javaEE.code.class12.DBCP;
import com.javaEE.code.class12.model.StudentHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentHomeworkJDBC {

    // 数据库连接
    private Connection connection = null;
    // 执行对象
    private PreparedStatement pstmt = null;

    // 查找所有记录
    public List<StudentHomework> selectAll() {

        // 定义 SQL 语句
        String sql = "SELECT * FROM s_student_homework";
        System.out.println(sql);
        // 一条结果
        StudentHomework sh = null;
        // 存储结果集
        List<StudentHomework> resultList = new ArrayList<>();
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            // 获取结果集
            ResultSet resultSet = pstmt.executeQuery(sql);
            // 输出结果集
            while (resultSet.next()) {
                // 初始化一条结果
                sh = new StudentHomework();
                sh.setId(resultSet.getLong("id"));
                sh.setStudentId(resultSet.getLong("student_id"));
                sh.setHomeworkId(resultSet.getLong("homework_id"));
                sh.setHomeworkTitle(resultSet.getString("homework_title"));
                sh.setHomeworkContent(resultSet.getString("homework_content"));
                sh.setCreateTime(resultSet.getTimestamp("create_time"));
                sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                resultList.add(sh);
                // 归还连接
                DBCP.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // 添加一条记录
    public void addStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "INSERT INTO s_student_homework(student_id, homework_id, homework_title, homework_content) VALUES (?, ?, ?, ?)";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, sh.getStudentId());
            pstmt.setLong(2, sh.getHomeworkId());
            pstmt.setString(3, sh.getHomeworkTitle());
            pstmt.setString(4, sh.getHomeworkContent());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除一条记录
    public void deleteStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "DELETE FROM s_student_homework WHERE id=?";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, sh.getId());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新一条记录
    public void updateStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "UPDATE s_student_homework SET student_id=?, homework_id=?, homework_title=?, homework_content=?, update_time=? WHERE id=?";
        //
        try {
            // 获取连接
            connection = DBCP.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, sh.getStudentId());
            pstmt.setLong(2, sh.getHomeworkId());
            pstmt.setString(3, sh.getHomeworkTitle());
            pstmt.setString(4, sh.getHomeworkContent());
            pstmt.setTimestamp(5, new Timestamp(new Date().getTime()));
            pstmt.setLong(6, sh.getId());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        StudentHomeworkJDBC shJDBC = new StudentHomeworkJDBC();
//        StudentHomework sh = new StudentHomework();

//         To test selectAll()
//        List<StudentHomework> list = shJDBC.selectAll();
//        for (StudentHomework sh : list)
//            System.out.println(sh.getHomeworkContent());

//         To test addStudentHomework(StudentHomework sh)
//        sh.setStudentId(104L);
//        sh.setHomeworkId(104L);
//        sh.setHomeworkTitle("作业");
//        sh.setHomeworkContent("www.google.com");
//        shJDBC.addStudentHomework(sh);

//         To test deleteStudentHomework(StudentHomework sh)
//        sh.setId(4L);
//        shJDBC.deleteStudentHomework(sh);

//         To test updateStudentHomework(StudentHomework sh)
//        sh.setId(11L);
//        sh.setHomeworkId(105L);
//        sh.setStudentId(105L);
//        sh.setHomeworkTitle("作业");
//        sh.setHomeworkContent("bjtu.edu.cn");
//        shJDBC.updateStudentHomework(sh);

//          shJDBC.closeAll();
//    }
}
