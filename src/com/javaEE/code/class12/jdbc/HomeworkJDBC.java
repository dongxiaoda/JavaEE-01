package com.javaEE.code.class12.jdbc;

import com.javaEE.code.class12.DBCP;
import com.javaEE.code.class12.model.Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeworkJDBC {

    // 数据库连接
    private Connection connection = null;
    // 执行对象
    private PreparedStatement pstmt = null;

    // 查找所有记录
    public List<Homework> selectAll() {
        // 定义 SQL 语句
        String sql = "SELECT * FROM s_homework";
        System.out.println(sql);
        // 一条结果
        Homework h = null;
        // 存储结果集
        List<Homework> resultList = new ArrayList<>();
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery(sql);
            // 输出结果集
            while (resultSet.next()) {
                // 初始化一条结果
                h = new Homework();
                h.setId(resultSet.getLong("id"));
                h.setHomeworkTittle(resultSet.getString("title"));
                h.setHomeworkContent(resultSet.getString("content"));
                h.setCreateTime(resultSet.getTimestamp("create_time"));
                h.setUpdateTime(resultSet.getTimestamp("update_time"));
                resultList.add(h);
                // 归还连接
                DBCP.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // 添加一条记录
    public void addHomework(Homework h) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "INSERT INTO s_homework(title, content) VALUES (?, ?)";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, h.getHomeworkTittle());
            pstmt.setString(2, h.getHomeworkContent());
            System.out.println(sql);
            pstmt.execute();
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除一条记录
    public void deleteHomework(Homework h) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "DELETE FROM s_homework WHERE id=?";
        // 执行 SQL
        try {
            // 获取连接
            connection = DBCP.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, h.getId());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新一条记录
    public void updateHomework(Homework h) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "UPDATE s_homework SET title=?, content=?, update_time=? WHERE id=?";
        //
        try {
            // 获取连接
            connection = DBCP.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, h.getHomeworkTittle());
            pstmt.setString(2, h.getHomeworkContent());
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setLong(4, h.getId());
            System.out.println(sql);
            pstmt.execute();
            // 归还连接
            DBCP.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        HomeworkJDBC hJDBC = new HomeworkJDBC();
//        Homework h = new Homework();

    // To test addHomework(Homework h)
//        h.setId(100L);
//        h.setHomeworkTittle("作业");
//        h.setHomeworkContent("www.github.com");
//        hJDBC.addHomework(h);

    // To test selectAll()
//        List<Homework> list = hJDBC.selectAll();
//        for (Homework homework : list)
//            System.out.println(homework.getHomeworkContent());

    // To test updateHomework(Homework h)
//        h.setId(100L);
//        h.setHomeworkTittle("测试作业");
//        h.setHomeworkContent("bjtu.edu.cn");
//        hJDBC.updateHomework(h);

    // To test deleteHomework(Homework h)
//        h.setId(100L);
//        hJDBC.deleteHomework(h);
//
//        hJDBC.closeAll();
//    }
}
